package com.shark.ringtone.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.minidev.json.JSONObject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shark.ringtone.utils.JsonDateSerializer;
/**
 * @author manoj.s
 *
 */
@Entity
@Table(name = "channels")
public class Channel implements Serializable{
	
	@Id
	String channelid;
	String name;
	String type;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date startdate;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date enddate;
	
	public Channel() {
		super();
	}

	public Channel(String channelid, String name, String type, Date startdate,
			Date enddate) {
		super();
		this.channelid = channelid;
		this.name = name;
		this.type = type;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Override
	public String toString() {
		return "Channel [channelid=" + channelid + ", name=" + name + ", type="
				+ type + ", startdate=" + startdate + ", enddate=" + enddate
				+ "]";
	}
	
	public JSONObject toJson(){
		JSONObject json = new JSONObject();
		json.put("channelid",channelid);
		json.put("name",name);
		json.put("type",type);
		return json;
	}
	
}
