/**
 * 
 */
package com.shark.ringtone.model;

import javax.annotation.RegEx;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * @author manoj.s
 *
 */
public class DownloadStatusRequest {
	
	@NotBlank
	String firebase_uid;
	@NotBlank
	String firebase_token;
	@NotBlank
	String contentid;
	@NotNull
	Long cut_start_time;
	@NotNull
	@Min(value = 1)
	Long cut_end_time;
	public DownloadStatusRequest() {
		super();
	}
	public DownloadStatusRequest(@NotBlank String firebase_uid,
			@NotBlank String firebase_token, @NotBlank String contentid,
			@NotNull long cut_start_time, @NotNull long cut_end_time) {
		super();
		this.firebase_uid = firebase_uid;
		this.firebase_token = firebase_token;
		this.contentid = contentid;
		this.cut_start_time = cut_start_time;
		this.cut_end_time = cut_end_time;
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
	@Override
	public String toString() {
		return "DownloadStatusRequest [firebase_uid=" + firebase_uid
				+ ", firebase_token=" + firebase_token + ", contentid="
				+ contentid + ", cut_start_time=" + cut_start_time
				+ ", cut_end_time=" + cut_end_time + "]";
	}
	
}
