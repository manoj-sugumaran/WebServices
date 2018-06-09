package com.shark.ringtone.model;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shark.ringtone.utils.JsonDateSerializer;

import net.minidev.json.JSONObject;
/**
 * @author manoj.s
 *
 */
@Entity
@Table(name = "app_user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created", "last_modified"}, 
        allowGetters = true)
public class User implements Serializable{
	
	@Id
	String firebase_uid;
	String username;
	String email;
	String mobileno;
	String uid;
	String authentication_type;
	String facebook_id;
	String twitter_handle;
	String profile_url;
	String os_version;
	String app_version;
	String location;
	String player_id;
	int is_active = 1;
	@JsonSerialize(using = JsonDateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    Date created;
	@JsonSerialize(using = JsonDateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    Date last_modified;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date photo_modified;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date password_modified;
	
	public User() {
		super();
	}

	public User(String firebase_uid, String username, String email, String mobileno, String uid,
			String authentication_type, String facebook_id, String twitter_handle, String profile_url,
			String os_version, String app_version, String location, String player_id, int is_active, Date created,
			Date last_modified, Date photo_modified, Date password_modified) {
		super();
		this.firebase_uid = firebase_uid;
		this.username = username;
		this.email = email;
		this.mobileno = mobileno;
		this.uid = uid;
		this.authentication_type = authentication_type;
		this.facebook_id = facebook_id;
		this.twitter_handle = twitter_handle;
		this.profile_url = profile_url;
		this.os_version = os_version;
		this.app_version = app_version;
		this.location = location;
		this.player_id = player_id;
		this.is_active = is_active;
		this.created = created;
		this.last_modified = last_modified;
		this.photo_modified = photo_modified;
		this.password_modified = password_modified;
	}

	public String getFirebase_uid() {
		return firebase_uid;
	}

	public void setFirebase_uid(String firebase_uid) {
		this.firebase_uid = firebase_uid;
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

	public String getAuthentication_type() {
		return authentication_type;
	}

	public void setAuthentication_type(String authentication_type) {
		this.authentication_type = authentication_type;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
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
		return "User [firebase_uid=" + firebase_uid + ", username=" + username + ", email=" + email + ", mobileno="
				+ mobileno + ", uid=" + uid + ", authentication_type=" + authentication_type + ", facebook_id="
				+ facebook_id + ", twitter_handle=" + twitter_handle + ", profile_url=" + profile_url + ", os_version="
				+ os_version + ", app_version=" + app_version + ", location=" + location + ", player_id=" + player_id
				+ ", is_active=" + is_active + ", created=" + created + ", last_modified=" + last_modified
				+ ", photo_modified=" + photo_modified + ", password_modified=" + password_modified + "]";
	}
	
	public JSONObject toJSON(){
		JSONObject json = new JSONObject();
		json.put("firebase_uid",firebase_uid);
		json.put("username",username);
		json.put("email",email);
		json.put("mobileno",mobileno);
		json.put("uid",uid);
		json.put("authentication_type",authentication_type);
		json.put("facebook_id",facebook_id);
		json.put("twitter_handle",twitter_handle);
		json.put("profile_url",profile_url);
		json.put("os_version",os_version);
		json.put("app_version",app_version);
		json.put("mcc",location);
		json.put("player_id",player_id);
		return json;
	}
	
}
