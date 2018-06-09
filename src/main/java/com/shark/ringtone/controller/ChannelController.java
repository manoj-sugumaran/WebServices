package com.shark.ringtone.controller;

import java.util.List;

import net.minidev.json.JSONArray;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shark.ringtone.RingtoneApplication;
import com.shark.ringtone.model.Channel;
import com.shark.ringtone.repository.ChannelRepository;
/**
 * @author manoj.s
 *
 */
@RestController
public class ChannelController {
	private final static Logger log = Logger.getLogger(ChannelController.class);

	@Autowired
	ChannelRepository channelRepository;
	
	@GetMapping(path = "/GetTags")
	public JSONArray getChannels(){
		
		log.info("Inside GetTags API \n Fetching all tags");
		 List<Channel> channelsList = channelRepository.findAll();
		JSONArray channels = new JSONArray();
		for(Channel c: channelsList)
			channels.add(c.toJson());
		return channels;
	}
}
