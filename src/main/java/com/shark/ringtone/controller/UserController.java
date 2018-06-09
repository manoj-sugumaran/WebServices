package com.shark.ringtone.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shark.ringtone.model.RegisterUserRequest;
import com.shark.ringtone.model.Subscription;
import com.shark.ringtone.model.User;
import com.shark.ringtone.model.UserRequest;
import com.shark.ringtone.model.UserSubscription;
import com.shark.ringtone.repository.SubscriptionsRepository;
import com.shark.ringtone.repository.UserRepository;
import com.shark.ringtone.repository.UserSubscriptionRepository;
import com.shark.ringtone.utils.FirebaseTokenAuthentication;

import net.minidev.json.JSONObject;


/**
 * @author manoj.s
 *
 */
@RestController
public class UserController {
	private final static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserSubscriptionRepository userSubscriptionRepository;
	
	@PostMapping(path = "/RegisterUser")
	public ResponseEntity<JSONObject> registerUser(@Valid @RequestBody RegisterUserRequest reqBody){
		
		log.info("Start of RegisterUser");
		log.info("Request Body:"+reqBody.toString());
		
		JSONObject response = new JSONObject();
		try {
			if(FirebaseTokenAuthentication.authenticate(reqBody.getFirebase_token(), reqBody.getFirebase_uid())){
				Boolean exist = userRepository.existsById(reqBody.getFirebase_uid());
				User user = requestToUser(reqBody, exist);
				userRepository.save(user);
				response.put("registered-user", true);
				log.info("End of RegisterUser");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}else {
				log.info("Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
				response.put("registered-user", false);
				response.put("reason","Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
		} catch (Exception e) {
			log.info("Exception:"+e);
			response.put("registered-user", false);
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping(path = "/CancelRegistration")
	public ResponseEntity<JSONObject> cancelRegistration(@Valid @RequestBody UserRequest reqBody){

		log.info("Start of CancelRegistration");
		log.info("Request Body:"+reqBody.toString());
		
		JSONObject response = new JSONObject();
		try {
			if(userRepository.existsActiveUser(reqBody.getFirebase_uid())){
				if(FirebaseTokenAuthentication.authenticate(reqBody.getFirebase_token(), reqBody.getFirebase_uid())){
					User user = userRepository.getOne(reqBody.getFirebase_uid());
					user.setIs_active(0);
					userRepository.save(user);
					response.put("removed-user", true);
					log.info("End of CancelRegistration");
					return ResponseEntity.status(HttpStatus.OK).body(response);
				}else {
					log.info("Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
					response.put("removed-user", false);
					response.put("reason","Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
				}
			}else{
				log.info("User with given firebase_uid does not exist");
				response.put("removed-user", false);
				response.put("reason","User doesn't exist!!!");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			log.info("Exception:"+e);
			response.put("removed-user", false);
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping(path = "/GetUserProfile")
	public ResponseEntity<JSONObject> getUserProfile(@Valid @RequestBody UserRequest reqBody){
		JSONObject response = new JSONObject();
		String firebase_uid = reqBody.getFirebase_uid();
		try {
			if(userRepository.existsActiveUser(firebase_uid)){
				if(FirebaseTokenAuthentication.authenticate(reqBody.getFirebase_token(),firebase_uid)){
					User user = userRepository.getOne(firebase_uid);
					response.put("profile", user.toJSON());
					if(userSubscriptionRepository.existsActiveSubscription(firebase_uid)){
						UserSubscription userSubscription = userSubscriptionRepository.getActiveUserSubscription(firebase_uid);
						response.put("user_subscription", userSubscription.toJson());
					}
					return ResponseEntity.status(HttpStatus.OK).body(response);
				}else {
					response.put("status", "failure");
					response.put("reason","Firebase ID token has expired or is not yet valid. Get a fresh token from your client app and try again");
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
				}
			}else{
				response.put("status", "failure");
				response.put("reason","User doesn't exist!!!");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.put("status", "failure");
			response.put("reason","Exception:"+e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	public User requestToUser(RegisterUserRequest reqBody, Boolean exist){
		
		User user = null;
		if(exist){
			user = userRepository.getOne(reqBody.getFirebase_uid());
		}else{
			user = new User();
		}
		user.setApp_version(reqBody.getApp_version());
		user.setAuthentication_type(reqBody.getFirebase_login_channel());
		user.setEmail(reqBody.getEmail());
		user.setFacebook_id(reqBody.getFacebook_id());
		user.setFirebase_uid(reqBody.getFirebase_uid());
		user.setLocation(reqBody.getMcc());
		user.setMobileno(reqBody.getMobileno());
		user.setOs_version(reqBody.getOs_version());
		user.setPassword_modified(reqBody.getPassword_modified());
		user.setPhoto_modified(reqBody.getPhoto_modified());
		user.setPlayer_id(reqBody.getPlayer_id());
		user.setProfile_url(reqBody.getProfile_url());
		user.setTwitter_handle(reqBody.getTwitter_handle());
		user.setUid(reqBody.getUid());
		user.setUsername(reqBody.getUsername());
		user.setIs_active(1);
		return user;
	}
	
}
