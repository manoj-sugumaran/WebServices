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
public class RegisterUserRequest {

	@Id
	@NotBlank
	String firebase_uid;
	@NotBlank
	String firebase_token;
	String username;
	String email;
	String mobileno;
	@NotBlank
	String uid;
	String firebase_login_channel;
	String facebook_id;
	String twitter_handle;
	String profile_url;
	String os_version;
	@NotBlank
	String app_version;
	@NotBlank
	String mcc;
	String mnc;
	String player_id;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	Date photo_modified;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	Date password_modified;
	
	
	
	public RegisterUserRequest(String firebase_uid, @NotBlank String firebase_token, String username, String email,
			String mobileno, @NotBlank String uid, String firebase_login_channel, String facebook_id,
			String twitter_handle, String profile_url, String os_version, @NotBlank String app_version,
			@NotBlank String mcc, String mnc, String player_id, Date photo_modified, Date password_modified) {
		super();
		this.firebase_uid = firebase_uid;
		this.firebase_token = firebase_token;
		this.username = username;
		this.email = email;
		this.mobileno = mobileno;
		this.uid = uid;
		this.firebase_login_channel = firebase_login_channel;
		this.facebook_id = facebook_id;
		this.twitter_handle = twitter_handle;
		this.profile_url = profile_url;
		this.os_version = os_version;
		this.app_version = app_version;
		this.mcc = mcc;
		this.mnc = mnc;
		this.player_id = player_id;
		this.photo_modified = photo_modified;
		this.password_modified = password_modified;
	}
	public RegisterUserRequest() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFirebase_login_channel() {
		return firebase_login_channel;
	}
	public void setFirebase_login_channel(String firebase_login_channel) {
		this.firebase_login_channel = firebase_login_channel;
	}
	public String getFacebook_id() {
		return facebook_id;
	}
	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}
	public String getTwitter_handle() {
		return twitter_handle;
	}
	public void setTwitter_handle(String twitter_handle) {
		this.twitter_handle = twitter_handle;
	}
	public String getProfile_url() {
		return profile_url;
	}
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public String getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}
	public Date getPhoto_modified() {
		return photo_modified;
	}
	public void setPhoto_modified(Date photo_modified) {
		this.photo_modified = photo_modified;
	}
	public Date getPassword_modified() {
		return password_modified;
	}
	public void setPassword_modified(Date password_modified) {
		this.password_modified = password_modified;
	}
	@Override
	public String toString() {
		return "RegisterUserRequest [firebase_uid=" + firebase_uid + ", firebase_token=" + firebase_token
				+ ", username=" + username + ", email=" + email + ", mobileno=" + mobileno + ", uid=" + uid
				+ ", firebase_login_channel=" + firebase_login_channel + ", facebook_id=" + facebook_id
				+ ", twitter_handle=" + twitter_handle + ", profile_url=" + profile_url + ", os_version=" + os_version
				+ ", app_version=" + app_version + ", mcc=" + mcc + ", mnc=" + mnc + ", player_id=" + player_id
				+ ", photo_modified=" + photo_modified + ", password_modified=" + password_modified + "]";
	}
	
	
	
}
