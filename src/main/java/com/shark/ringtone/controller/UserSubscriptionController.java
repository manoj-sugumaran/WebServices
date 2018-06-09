/**
 * 
 */
package com.shark.ringtone.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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

import com.shark.ringtone.model.ChannelMap;
import com.shark.ringtone.model.Content;
import com.shark.ringtone.model.Subscription;
import com.shark.ringtone.model.Subscription;
import com.shark.ringtone.model.SubscriptionRequest;
import com.shark.ringtone.model.User;
import com.shark.ringtone.model.UserRequest;
import com.shark.ringtone.model.UserSubscription;
import com.shark.ringtone.repository.SubscriptionsRepository;
import com.shark.ringtone.repository.UserRepository;
import com.shark.ringtone.repository.UserSubscriptionRepository;
import com.shark.ringtone.utils.FirebaseTokenAuthentication;

/**
 * @author manoj.s
 *
 */
@RestController
public class UserSubscriptionController {
	
	private final static Logger log = LoggerFactory.getLogger(UserSubscriptionController.class);

	@Autowired
	UserSubscriptionRepository userSubscriptionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SubscriptionsRepository subscriptionRepository;
	
	@PostMapping( path = "/CheckDownloadStatus")
	public ResponseEntity<JSONObject> checkDownloadStatus(@Valid @RequestBody UserRequest reqBody){
		
		log.info("Start of CheckDownloadStatus");
		log.info("Request Body:"+reqBody.toString());
		
		JSONObject response = new JSONObject();
		String firebase_uid = reqBody.getFirebase_uid();
		try {
			if(userRepository.existsActiveUser(firebase_uid)){
				if(FirebaseTokenAuthentication.authenticate(reqBody.getFirebase_token(), firebase_uid)){
					UserSubscription userSubscription = userSubscriptionRepository.getActiveUserSubscription(firebase_uid);
					if(userSubscription!=null){
						Subscription  subscription = subscriptionRepository.getOne(userSubscription.getSubscription_id());
						if(subscription!=null){
							if(userSubscription.getNumber_of_downloads() < subscription.getDownload_limit() && new Date().compareTo(userSubscription.getSubscription_end_date())<0){
								//allowed to download
								response.put("download_allowed", true);
								response.put("download_count", userSubscription.getNumber_of_downloads());
								response.put("download_limit", subscription.getDownload_limit());
								log.info("End of CheckDownloadStatus");
								return ResponseEntity.status(HttpStatus.OK).body(response);
							}else{
								//Not allowed to download
								response.put("download_allowed", false);
								response.put("download_count", userSubscription.getNumber_of_downloads());
								response.put("download_limit", subscription.getDownload_limit());
								log.info("End of CheckDownloadStatus");
								return ResponseEntity.status(HttpStatus.OK).body(response);
							}
						}else{
							//no subscription with subscription id found in subscription table
							log.info("No subscription with the subscription id found");
							response.put("status", "failure");
							response.put("reason", "No subscription with the subscription id found");
							return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
						}
					}else{
						// if user is not actively subscribed to any pack
						log.info("No active user subscription found. Subscribe to any pack and check");
						response.put("status", "failure");
						response.put("reason", "No active user subscription found. Subscribe to any pack and check");
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
					}
				}else {
					log.info("Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
					response.put("status", "failure");
					response.put("reason","Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
				}
			}else{
				log.info("User with given firebase_uid does not exist");
				response.put("status", "failure");
				response.put("reason","User doesn't exist!!!");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			log.info("Exception:"+e);
			response.put("status", "failure");
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping( path = "/UpdateSubscription")
	public ResponseEntity<JSONObject> updateSubscription(@Valid @RequestBody SubscriptionRequest reqBody){
		
		log.info("Start of UpdateSubscription");
		log.info("Request Body:"+reqBody.toString());
		
		JSONObject response = new JSONObject();
		String firebase_uid = reqBody.getFirebase_uid();
		try {
			if(userRepository.existsActiveUser(firebase_uid)){
				if(FirebaseTokenAuthentication.authenticate(reqBody.getFirebase_token(),firebase_uid)){
					
					//check if subscription exists 
					String subscription_type = reqBody.getSubscription_type();
					Subscription subscription = subscriptionRepository.getOne(reqBody.getSubscription_id());
					if(subscription==null){
						log.info("No subscription with the subscription id found");
						response.put("status", "failure");
						response.put("reason", "No subscription with the subscription id found");
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
					}
					UserSubscription userSubscription = null;
					if(userSubscriptionRepository.existsActiveSubscription(firebase_uid)){
						userSubscription = userSubscriptionRepository.getActiveUserSubscription(firebase_uid);
						if(!userSubscription.getSubscription_id().equals(reqBody.getSubscription_id()) || 
								(userSubscription.getSubscription_id().equals(reqBody.getSubscription_id()) && subscription_type.equalsIgnoreCase("SUBSCRIPTION_PURCHASED"))){
							log.info("User can have only one subscription at a time, please cancel the current subscription and make request again");
							response.put("status", "failure");
							response.put("reason", "User can have only one subscription at a time, please cancel the current subscription and make request again");
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
						}
					}else{
						userSubscription = new UserSubscription();
					}
					
					Date curDate = new Date();
					Calendar c = Calendar.getInstance();
				    c.setTime(curDate);
				    c.add(Calendar.DATE, subscription.getBilling_period_days()); 
				    Date curEndDate = c.getTime();
				    
					switch(subscription_type){    
					case "SUBSCRIPTION_PURCHASED":    
						 // make new entry to db.   
						  userSubscription.setSubscription_end_date(curEndDate);
						  userSubscription.setNumber_of_downloads(0);
						  userSubscription.setSubscription_type(subscription_type);
						  userSubscription.setFirebase_uid(firebase_uid);
						  userSubscription.setSubscription_id(reqBody.getSubscription_id());  
						  userSubscriptionRepository.save(userSubscription);
						  
						  response.put("subscription-updated", true);
						  log.info("End of UpdateSubscription");
						  return ResponseEntity.status(HttpStatus.OK).body(response);
					
					case "SUBSCRIPTION_RENEWED":    
						 // change just subscription end date and reset download count
					    userSubscription.setSubscription_end_date(curEndDate);
						userSubscription.setNumber_of_downloads(0);
						userSubscription.setSubscription_type(subscription_type);
						userSubscriptionRepository.save(userSubscription);

						
						response.put("subscription-updated", true);
						log.info("End of UpdateSubscription");
						return ResponseEntity.status(HttpStatus.OK).body(response);
					
					case "SUBSCRIPTION_CANCELLED":    
						 // change the subscription end date to current date and make status as 0
						userSubscription.setSubscription_end_date(new Date());
						userSubscription.setStatus(0);
						userSubscription.setSubscription_type(subscription_type);
						userSubscriptionRepository.save(userSubscription);

						  
						response.put("subscription-updated", true);
						log.info("End of UpdateSubscription");
						return ResponseEntity.status(HttpStatus.OK).body(response);
						
					default:     
							log.info("Invalid Subscription type");
							response.put("status", "failure");
							response.put("reason","Invalid Subscription type");
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
					}
				}else {
					log.info("Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
					response.put("status", "failure");
					response.put("reason","Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
				}
			}else{
				log.info("User with given firebase_uid does not exist");
				response.put("status", "failure");
				response.put("reason","User doesn't exist!!!");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			log.info("Exception:"+e);
			response.put("status", "failure");
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
