/**
 * 
 */
package com.shark.ringtone.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.minidev.json.JSONObject;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shark.ringtone.utils.JsonDateSerializer;

/**
 * @author manoj.s
 *
 */
@Entity
@Table(name = "user_subscription")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"subscription_start_date"}, 
        allowGetters = true)
public class UserSubscription implements Serializable{
	
	@Id
	String id = UUID.randomUUID().toString();
	String firebase_uid;
	String subscription_id;
	@JsonSerialize(using = JsonDateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	Date subscription_start_date;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date subscription_end_date;
	int number_of_downloads;
	String subscription_type;
	int status = 1;
	public UserSubscription() {
		super();
	}
	public UserSubscription(String id, String firebase_uid,
			String subscription_id, Date subscription_start_date,
			Date subscription_end_date, int number_of_downloads,
			String subscription_type, int status) {
		super();
		this.id = id;
		this.firebase_uid = firebase_uid;
		this.subscription_id = subscription_id;
		this.subscription_start_date = subscription_start_date;
		this.subscription_end_date = subscription_end_date;
		this.number_of_downloads = number_of_downloads;
		this.subscription_type = subscription_type;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirebase_uid() {
		return firebase_uid;
	}
	public void setFirebase_uid(String firebase_uid) {
		this.firebase_uid = firebase_uid;
	}
	public String getSubscription_id() {
		return subscription_id;
	}
	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}
	public Date getSubscription_start_date() {
		return subscription_start_date;
	}
	public void setSubscription_start_date(Date subscription_start_date) {
		this.subscription_start_date = subscription_start_date;
	}
	public Date getSubscription_end_date() {
		return subscription_end_date;
	}
	public void setSubscription_end_date(Date subscription_end_date) {
		this.subscription_end_date = subscription_end_date;
	}
	public int getNumber_of_downloads() {
		return number_of_downloads;
	}
	public void setNumber_of_downloads(int number_of_downloads) {
		this.number_of_downloads = number_of_downloads;
	}
	public String getSubscription_type() {
		return subscription_type;
	}
	public void setSubscription_type(String subscription_type) {
		this.subscription_type = subscription_type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserSubscription [id=" + id + ", firebase_uid=" + firebase_uid
				+ ", subscription_id=" + subscription_id
				+ ", subscription_start_date=" + subscription_start_date
				+ ", subscription_end_date=" + subscription_end_date
				+ ", number_of_downloads=" + number_of_downloads
				+ ", subscription_type=" + subscription_type + ", status="
				+ status + "]";
	}
	
	public JSONObject toJson(){
		JSONObject json = new JSONObject();
		json.put("subscription_id",subscription_id);
		json.put("number_of_downloads",number_of_downloads);
		json.put("subscription_start_date",subscription_start_date);
		json.put("subscription_end_date",subscription_end_date);
		json.put("subscription_type", subscription_type);
		return json;

	}
}

