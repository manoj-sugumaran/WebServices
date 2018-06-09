package com.shark.ringtone.model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
/**
 * @author manoj.s
 *
 */
public class UserRequest {

	@Id
	@NotBlank
	String firebase_uid;
	@NotBlank
	String firebase_token;
	public UserRequest() {
		super();
	}
	public UserRequest(@NotBlank String firebase_uid, @NotBlank String firebase_token) {
		super();
		this.firebase_uid = firebase_uid;
		this.firebase_token = firebase_token;
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
	@Override
	public String toString() {
		return "UserRequest [firebase_uid=" + firebase_uid + ", firebase_token=" + firebase_token + "]";
	}
	
	
}
