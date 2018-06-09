/**
 * 
 */
package com.shark.ringtone.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shark.ringtone.utils.JsonDateDeserializer;

/**
 * @author manoj.s
 *
 */
public class DownloadHistoryRequest {

	@NotBlank
	String firebase_uid;
	@NotBlank
	String firebase_token;
	@NotNull
	@JsonDeserialize(using = JsonDateDeserializer.class)
	Date start_date;
	@NotNull
	@JsonDeserialize(using = JsonDateDeserializer.class)
	Date end_date;
	@NotNull
	@Min(value = 1)
	Integer limit;
	int offset = 0;
	public DownloadHistoryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DownloadHistoryRequest(@NotBlank String firebase_uid,
			@NotBlank String firebase_token, @NotNull Date start_date,
			@NotNull Date end_date, @NotNull int limit, int offset) {
		super();
		this.firebase_uid = firebase_uid;
		this.firebase_token = firebase_token;
		this.start_date = start_date;
		this.end_date = end_date;
		this.limit = limit;
		this.offset = offset;
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
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	@Override
	public String toString() {
		return "DownloadHistoryRequest [firebase_uid=" + firebase_uid
				+ ", firebase_token=" + firebase_token + ", start_date="
				+ start_date + ", end_date=" + end_date + ", limit=" + limit
				+ ", offset=" + offset + "]";
	}
}
