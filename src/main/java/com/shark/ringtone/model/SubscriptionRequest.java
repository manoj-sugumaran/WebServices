package com.shark.ringtone.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shark.ringtone.utils.JsonDateDeserializer;
/**
 * @author manoj.s
 *
 */
public class SubscriptionRequest {

	@Id
	@NotBlank
	String subscription_id;
	@NotBlank
	String firebase_uid;
	@NotBlank
	String firebase_token;
	@NotBlank
	String subscription_type;
	public SubscriptionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubscriptionRequest(@NotBlank String subscription_id,
			@NotBlank String firebase_uid, @NotBlank String firebase_token,
			@NotBlank String subscription_type) {
		super();
		this.subscription_id = subscription_id;
		this.firebase_uid = firebase_uid;
		this.firebase_token = firebase_token;
		this.subscription_type = subscription_type;
	}
	public String getSubscription_id() {
		return subscription_id;
	}
	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}
	public String getFirebase_uid() {
		return firebase_uid;
	}
	public void setFirebase_uid(String firebase_uid) {
		this.firebase_uid = firebase_uid;
	}
	public String getFirebase_token() {
		return firebase_token;
	}
	public void setFirebase_token(String firebase_token) {
		this.firebase_token = firebase_token;
	}
	public String getSubscription_type() {
		return subscription_type;
	}
	public void setSubscription_type(String subscription_type) {
		this.subscription_type = subscription_type;
	}
	@Override
	public String toString() {
		return "SubscriptionRequest [subscription_id=" + subscription_id
				+ ", firebase_uid=" + firebase_uid + ", firebase_token="
				+ firebase_token + ", subscription_type=" + subscription_type
				+ "]";
	}
	
	
	
	
}
