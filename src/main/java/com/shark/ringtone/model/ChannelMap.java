package com.shark.ringtone.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shark.ringtone.utils.JsonDateSerializer;
/**
 * @author manoj.s
 *
 */
@Entity
@Table (name = "channelmap")
@Cacheable
public class ChannelMap implements Serializable{
	
	@EmbeddedId
	private ChannelMapKey channelMapKey;
	long contentorder;
	String context_teaser;
	String context_trivia;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date context_start_date;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date context_end_date;
	public ChannelMap() {
		super();
	}
	public ChannelMap(ChannelMapKey channelMapKey, long contentorder, String context_teaser, String context_trivia,
			Date context_start_date, Date context_end_date) {
		super();
		this.channelMapKey = channelMapKey;
		this.contentorder = contentorder;
		this.context_teaser = context_teaser;
		this.context_trivia = context_trivia;
		this.context_start_date = context_start_date;
		this.context_end_date = context_end_date;
	}
	public ChannelMapKey getChannelMapKey() {
		return channelMapKey;
	}
	public void setChannelMapKey(ChannelMapKey channelMapKey) {
		this.channelMapKey = channelMapKey;
	}
	public long getContentorder() {
		return contentorder;
	}
	public void setContentorder(long contentorder) {
		this.contentorder = contentorder;
	}
	public String getContext_teaser() {
		return context_teaser;
	}
	public void setContext_teaser(String context_teaser) {
		this.context_teaser = context_teaser;
	}
	public String getContext_trivia() {
		return context_trivia;
	}
	public void setContext_trivia(String context_trivia) {
		this.context_trivia = context_trivia;
	}
	public Date getContext_start_date() {
		return context_start_date;
	}
	public void setContext_start_date(Date context_start_date) {
		this.context_start_date = context_start_date;
	}
	public Date getContext_end_date() {
		return context_end_date;
	}
	public void setContext_end_date(Date context_end_date) {
		this.context_end_date = context_end_date;
	}
	@Override
	public String toString() {
		return "ChannelMap [channelMapKey=" + channelMapKey + ", contentorder=" + contentorder + ", context_teaser="
				+ context_teaser + ", context_trivia=" + context_trivia + ", context_start_date=" + context_start_date
				+ ", context_end_date=" + context_end_date + "]";
	}
	
	
}
