package com.shark.ringtone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shark.ringtone.model.ChannelMap;
import com.shark.ringtone.model.ChannelMapKey;
import com.shark.ringtone.repository.ChannelMapRepository;
/**
 * @author manoj.s
 *
 */
@RestController
public class ChannelMapController {
	
	@Autowired
    ChannelMapRepository channelMapRepository;
	
}
