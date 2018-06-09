/**
 * 
 */
package com.shark.ringtone.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
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
@Table (name = "downloaded_tracks")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"downloadedtime"}, 
        allowGetters = true)
public class DownloadedTracks implements Serializable {
	
	@Id
	String id = UUID.randomUUID().toString();
	String firebase_uid;
	String subscription_id;
	String contentid;
	long cut_start_time;
	long cut_end_time;
	@JsonSerialize(using = JsonDateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	Date downloadedtime;
	
	public DownloadedTracks() {
		super();
	}

	public DownloadedTracks(String id, String firebase_uid,
			String subscription_id, String contentid, long cut_start_time,
			long cut_end_time, Date downloadedtime) {
		super();
		this.id = id;
		this.firebase_uid = firebase_uid;
		this.subscription_id = subscription_id;
		this.contentid = contentid;
		this.cut_start_time = cut_start_time;
		this.cut_end_time = cut_end_time;
		this.downloadedtime = downloadedtime;
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

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public long getCut_start_time() {
		return cut_start_time;
	}

	public void setCut_start_time(long cut_start_time) {
		this.cut_start_time = cut_start_time;
	}

	public long getCut_end_time() {
		return cut_end_time;
	}

	public void setCut_end_time(long cut_end_time) {
		this.cut_end_time = cut_end_time;
	}

	public Date getDownloadedtime() {
		return downloadedtime;
	}

	public void setDownloadedtime(Date downloadedtime) {
		this.downloadedtime = downloadedtime;
	}

	@Override
	public String toString() {
		return "DownloadedTracks [id=" + id + ", firebase_uid=" + firebase_uid
				+ ", subscription_id=" + subscription_id + ", contentid="
				+ contentid + ", cut_start_time=" + cut_start_time
				+ ", cut_end_time=" + cut_end_time + ", downloadedtime="
				+ downloadedtime + "]";
	}

	
}
