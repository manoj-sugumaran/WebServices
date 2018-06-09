/**
 * 
 */
package com.shark.ringtone.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shark.ringtone.repository.ConfigRepository;
import com.shark.ringtone.utils.EncoderHelper;

/**
 * @author manoj.s
 *
 */  
@RestController
public class MadeController {

	private static Logger log = LoggerFactory.getLogger(MadeController.class);

	@Autowired
    ConfigRepository configRepository;
	
	@GetMapping(path = "/GetMadeUrl")
	public String getMadeUrl(@RequestParam(name="content_url_rt", required = true) String content_url_rt){
		List<String> encoderParameterList = EncoderHelper.getEncoderParameter(content_url_rt);
		String key = com.onmobile.generatecodes.GenerateCode.urlEncrypter(EncoderHelper.getStream(), encoderParameterList);
		String streamUrl = configRepository.getOne("MADE_PREVIEW").getValue() + key + ".aac";
		log.info("Stream Url:"+streamUrl);
		return streamUrl;
	}

}
