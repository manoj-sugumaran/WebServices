package com.shark.ringtone.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shark.ringtone.model.ChannelMap;
import com.shark.ringtone.model.ChannelMapKey;
import com.shark.ringtone.model.Config;
import com.shark.ringtone.model.Content;
import com.shark.ringtone.model.UpdateContentRequest;
import com.shark.ringtone.repository.ChannelMapRepository;
import com.shark.ringtone.repository.ContentRepository;
import com.shark.ringtone.repository.UserRepository;
import com.shark.ringtone.utils.Properties;
import com.shark.ringtone.utils.Constants;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
/**
 * @author manoj.s
 *
 */
@RestController
public class ContentController {
	
	private static Logger log = LoggerFactory.getLogger(ContentController.class);
	@Autowired
    ContentRepository contentRepository;
	@Autowired
	ChannelMapRepository channelMapRepository;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(path = "/GetContent")
	public ResponseEntity<JSONObject> getContent(@RequestParam(name="limit", required = true) int limit,
			@RequestParam(name="firebase_uid", required = true) String firebase_uid,
			@RequestParam(name="offset", required = false, defaultValue = "0") int offset,
			@RequestParam(name="channelid", required = false) String channelid,
			@RequestParam(name="keyword", required = false) String keyword,
			@RequestParam(name="mood", required = false, defaultValue = "false") boolean mood,
			@RequestParam(name="session_id", required = false, defaultValue = "empty") String session_id,
			@RequestParam(name="cut_allowed", required = false, defaultValue = "999") int cut_allowed){
		
		log.info("Start of GetContent API");
		log.info("firebase_uid:"+firebase_uid);
		log.info("offset:"+offset);
		log.info("channelid:"+channelid);
		log.info("keyword:"+keyword);
		log.info("session_id:"+session_id);
		log.info("Cut allowed:"+cut_allowed);

		JSONObject response = new JSONObject();
		JSONArray contentArray = new JSONArray();
		try {
			if(userRepository.existsActiveUser(firebase_uid)){
				
				log.info("firebase_uid:"+firebase_uid+" is valid");

				if(keyword!=null && !keyword.equalsIgnoreCase("")){
					log.info("Keyword is not null, hence calling msearch");
					String responseFromMSearch = null;
					String url ;
					JSONParser jsonParser = new JSONParser(JSONParser.MODE_PERMISSIVE);
					keyword = keyword.replace(" ", "%20");
					keyword = keyword.replace("&", "%26");
					if(mood){
						//search msearch with mood
						url = Properties.getPropertyValue(Constants.MSEARCH_MOOD);
					}else{
						//search msearch for all
						url = Properties.getPropertyValue(Constants.MSEARCH_KEYWORD);
					}
					url = url.replace("%keyword%", keyword);
					url = url.replace("%limit%", String.valueOf(limit));
					url = url.replace("%offset%", String.valueOf(offset));

					if(session_id!= null && !session_id.trim().equalsIgnoreCase(""))
						url += "&session_id="+session_id;
					responseFromMSearch = callURL(url);
					
					log.info("Msearch URL:"+url);
					JSONArray jsonArrayOfTracks = null;
					if(responseFromMSearch!=null){
						JSONObject tracksObject = (JSONObject) jsonParser.parse(responseFromMSearch);
						if(tracksObject.containsKey("tracks"))
							jsonArrayOfTracks = (JSONArray) tracksObject.get("tracks");
						log.debug("session_id: " + tracksObject.get("session_id"));
						session_id = (String) tracksObject.get("session_id");	
					}else{
						log.info("Got Null response/ Connection time out from Msearch");
						response.put("status", "failure");
						response.put("reason","Got Null response/ Connection time out from Msearch");
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
					}
					if(jsonArrayOfTracks != null && jsonArrayOfTracks.size() > 0){
						log.info("Getting tracks from Shark based on MSearch RBTID");
						for (int j = 0; j < jsonArrayOfTracks.size();j++) {
							JSONObject trackObject = (JSONObject) jsonArrayOfTracks.get(j);
							String contentid = (String) trackObject.get("RBTID");
							log.info("MSearch RBTID: " + contentid);
							if(cut_allowed!=999){
								if(!contentRepository.existsById_cut(contentid, cut_allowed))
									continue;
								Content c = contentRepository.getOne_cut(contentid, cut_allowed);
								contentArray.add(c.toJson());
							}else{
								if(!contentRepository.existsById(contentid))
									continue;
								Content c = contentRepository.getOne(contentid);
								contentArray.add(c.toJson());
							}
							
						}
					}
							
					offset += limit;
					
				}else{
					
					log.info("Getting contents from internal shark(Not hitting msearch)");
					List<ChannelMap> mapList = null ;
					// get contents from shark
					if(channelid!=null && !channelid.equalsIgnoreCase("")){
						if(channelid.equals(Properties.getPropertyValue(Constants.STORY_CHANNELID))){
							//Get stories

							if(cut_allowed!=999)
								mapList = channelMapRepository.findByChannelMapStories_cut(channelid, cut_allowed, new PageRequest(offset,limit));
							else
								mapList = channelMapRepository.findByChannelMapStories(channelid, new PageRequest(offset,limit));
						}else{
							//Get stories from a particular channelid
							if(cut_allowed!=999)
								mapList = channelMapRepository.findByChannelMapKeyChannelid_cut(channelid,cut_allowed, new PageRequest(offset,limit));
							else
								 mapList = channelMapRepository.findByChannelMapKeyChannelid(channelid, new PageRequest(offset,limit));
						}
					}else{
						//get merchandized contents
						if(cut_allowed!=999)
							mapList = channelMapRepository.findByChannelMapMerchandized_cut(cut_allowed, new PageRequest(offset,limit));
						else
							mapList = channelMapRepository.findByChannelMapMerchandized(new PageRequest(offset,limit));
					}
					if(mapList!=null && mapList.size()>0){
						for(ChannelMap map: mapList){
							 Content c = contentRepository.getOne(map.getChannelMapKey().getContentid());
							 contentArray.add(c.toJsonChannelMap(map));
						}	
					}	
					++offset;
				}
			}else{
				log.info("firebase_uid:"+firebase_uid+" does not exist");
				response.put("status", "Cannot retrieve contents");
				response.put("reason","User doesn't exist!!!");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		}catch (Exception e) {
			log.info("Exception:"+e);
			response.put("status", "failure");
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

		response.put("content",contentArray);
		response.put("offset",offset);
		if(!session_id.equalsIgnoreCase("empty"))
			response.put("session_id",session_id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping(path = "/GetAllContents")
	public void getAllContents(){		
		Date d  = new Date();
		// Get current size of heap in bytes
		long currBefore = Runtime.getRuntime().totalMemory()/(1024*1024); 
		// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
		long maxBefore = Runtime.getRuntime().maxMemory()/(1024*1024);
		// Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
		long freeBefore = Runtime.getRuntime().freeMemory()/(1024*1024); 
				
		contentRepository.findAll();
		log.info("Before time:"+ d);
		log.info("After Time:"+new Date());
		// Get current size of heap in bytes
		log.info("Current Size Before:"+currBefore); 
		// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
		log.info("Max Size Before:"+maxBefore);
		// Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
		log.info("Free memory Before:"+freeBefore); 
				
		// Get current size of heap in bytes
		log.info("Current Size After:"+Runtime.getRuntime().totalMemory()/(1024*1024)); 
		// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
		log.info("Max Size After:"+Runtime.getRuntime().maxMemory()/(1024*1024));
		// Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
		log.info("Free memory After:"+Runtime.getRuntime().freeMemory()/(1024*1024)); 
	}
	
	
	@GetMapping(path = "/GetContentInfo")
	public  ResponseEntity<JSONObject> getContentInfo(@RequestParam(name="contentid", required = true) String contentid,
			@RequestParam(name="firebase_uid", required = true) String firebase_uid){
		
		JSONObject response = new JSONObject();
		JSONArray contentArray = new JSONArray();

		try{
			if(userRepository.existsActiveUser(firebase_uid)){
				if(contentRepository.existsById(contentid)){
					 Content c = contentRepository.getOne(contentid);
					 contentArray.add(c.toJson());
					 response.put("content",contentArray);
					 return ResponseEntity.status(HttpStatus.OK).body(response);
				}else{
					response.put("status", "Cannot retrieve content");
					response.put("reason","content doesn't exist!!!");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
				}
			}else{
				response.put("status", "Cannot retrieve content");
				response.put("reason","User doesn't exist");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		}catch (Exception e) {
			response.put("status", "failure");
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	
	public static String callURL(String myURL) {
		StringWriter sw = new StringWriter();
		PrintWriter printWriter = new PrintWriter(sw);
		URLConnection urlConn = null;
		InputStreamReader in = null;
		//System.out.println("Requsted URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 10000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		}catch (Exception e) {
			e.printStackTrace(printWriter);
			log.info("Error->"+sw);
			return null;
		} 
		return sb.toString();
	}
	
	@PostMapping(path = "/UpdateContent")
	public ResponseEntity<JSONObject> updateContent(@RequestBody UpdateContentRequest updateContentRequest){
		
		Content c = contentRepository.getOne(updateContentRequest.getContentid());
		c.setSong_teaser(updateContentRequest.getTeaser());
		c.setSong_trivia(updateContentRequest.getTrivia());
		JSONObject response = new JSONObject();
		log.info("Updating Content with contentid :"+updateContentRequest.getContentid());
		contentRepository.save(c);
		response.put("status", "success");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
