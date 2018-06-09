/**
 * 
 */
package com.shark.ringtone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shark.ringtone.model.Subscription;
import com.shark.ringtone.repository.SubscriptionsRepository;

/**
 * @author manoj.s
 *
 */
@RestController
public class SubscriptionsController {
	
	@Autowired
	SubscriptionsRepository subscriptionsRepository;
	
	@GetMapping(path = "/GetSubscriptions")
	public List<Subscription> getSubsciptions(){
		return subscriptionsRepository.findAll();
	}

}
