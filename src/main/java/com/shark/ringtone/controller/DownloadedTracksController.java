/**
 * 
 */
package com.shark.ringtone.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shark.ringtone.model.Content;
import com.shark.ringtone.model.DownloadHistoryRequest;
import com.shark.ringtone.model.DownloadStatusRequest;
import com.shark.ringtone.model.DownloadedTracks;
import com.shark.ringtone.model.Subscription;
import com.shark.ringtone.model.UserSubscription;
import com.shark.ringtone.repository.ContentRepository;
import com.shark.ringtone.repository.DownloadedTracksRepository;
import com.shark.ringtone.repository.SubscriptionsRepository;
import com.shark.ringtone.repository.UserRepository;
import com.shark.ringtone.repository.UserSubscriptionRepository;
import com.shark.ringtone.utils.FirebaseTokenAuthentication;


/**
 * @author manoj.s
 *
 */
@RestController
public class DownloadedTracksController {

	private static Logger log = LoggerFactory.getLogger(DownloadedTracksController.class);

	@Autowired
	DownloadedTracksRepository downloadedTracksRepository;
	@Autowired
    ContentRepository contentRepository;
	@Autowired
	UserSubscriptionRepository userSubscriptionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SubscriptionsRepository subscriptionRepository;
	
	@PostMapping(path = "/UpdateContentDownloadStatusForRT")
	public ResponseEntity<JSONObject> updateDownloadStatus(@Valid @RequestBody DownloadStatusRequest reqBody){
		
		log.info("Start of UpdateContentDownloadStatusForRT");
		log.info("Request Body:"+reqBody.toString());
		
		JSONObject response = new JSONObject();
		String firebase_uid = reqBody.getFirebase_uid();
		try {
			if(FirebaseTokenAuthentication.authenticate(reqBody.getFirebase_token(),firebase_uid )){
				
				if(!userRepository.existsActiveUser(firebase_uid)){
					log.info("User with given firebase_uid does not exist");
					response.put("status", "failure");
					response.put("reason","User doesn't exist!!!");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
				}
				
				if(!userSubscriptionRepository.existsActiveSubscription(firebase_uid)){
					log.info("User with given firebase_uid does not have a active subscription");
					response.put("status", "failure");
					response.put("reason","User is not actively subscribed to any subscription");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
				UserSubscription userSubscription = userSubscriptionRepository.getActiveUserSubscription(firebase_uid);
				log.info("Current userSubscription :"+userSubscription.getSubscription_id());
				int currDownloadCount = userSubscription.getNumber_of_downloads();
				DownloadedTracks d = new DownloadedTracks();
				d.setContentid(reqBody.getContentid());
				d.setFirebase_uid(reqBody.getFirebase_uid());
				d.setCut_end_time(reqBody.getCut_end_time());
				d.setCut_start_time(reqBody.getCut_start_time());
				d.setSubscription_id(userSubscription.getSubscription_id());
				
				Content content = contentRepository.getOne(reqBody.getContentid());
				//Check whether use is in trial period
				Subscription subscription = subscriptionRepository.getOne(userSubscription.getSubscription_id());
				Date currentDate = new Date();
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(userSubscription.getSubscription_start_date());
	    		cal.add(Calendar.DATE, (int) subscription.getFree_trial_days());
	    		Date freeTrialExpiryDate = new Date(cal.getTime().getTime());
				if(currentDate.after(freeTrialExpiryDate)){
					 if(content.getCharge_class()==null || ( content.getCharge_class()!=null && !content.getCharge_class().equalsIgnoreCase("Free")))
						 userSubscription.setNumber_of_downloads(++currDownloadCount);
					 else
						log.info("Content is free, hence download count is not increased");
				}else{
					log.info("Current userSubscription is in free trial period, hence download count is not increased");
				}
								
				downloadedTracksRepository.save(d);
				response.put("status", "success");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}else {
				log.info("Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
				response.put("status", "failure");
				response.put("reason","Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
		} catch (Exception e) {
			log.info("Exception:"+e);
			response.put("status", "failure");
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping(path = "/GetDownloadHistory")
	public ResponseEntity<JSONObject> getDownloadHistory(@Valid @RequestBody DownloadHistoryRequest reqBody){
		
		
		log.info("Start of GetDownloadHistory");
		log.info("Request Body:"+reqBody.toString());
		
		int offset = reqBody.getOffset();
		int limit = reqBody.getLimit();
		JSONObject response = new JSONObject();
		JSONArray contentArray = new JSONArray();
		try {
			if(FirebaseTokenAuthentication.authenticate(reqBody.getFirebase_token(), reqBody.getFirebase_uid())){

				if(!userRepository.existsActiveUser(reqBody.getFirebase_uid())){
					log.info("User with given firebase_uid does not exist");
					response.put("status", "failure");
					response.put("reason","User doesn't exist!!!");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
				}
				
				List<DownloadedTracks> downloadedTracks = null;
				downloadedTracks = downloadedTracksRepository.getDownloadHisory(reqBody.getFirebase_uid(), reqBody.getStart_date(), reqBody.getEnd_date(), new PageRequest(offset,limit));
				if(downloadedTracks!=null && downloadedTracks.size()>0){
					for(DownloadedTracks track: downloadedTracks){
						 Content c = contentRepository.getOne(track.getContentid());
						 contentArray.add(c.toJsonDownloadHistory(track));
					}	
				}	
			}else {
				log.info("Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
				response.put("status", "failure");
				response.put("reason","Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
		} catch (Exception e) {
			log.info("Exception:"+e);
			response.put("status", "failure");
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("content",contentArray);
		response.put("offset",++offset);
		
		log.info("End of GetDownloadHistory");

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
