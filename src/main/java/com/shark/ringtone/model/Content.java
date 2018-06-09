package com.shark.ringtone.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shark.ringtone.utils.JsonDateSerializer;

import net.minidev.json.JSONObject;
/**
 * @author manoj.s
 *
 */
@Entity
@Table (name = "content")
@Cacheable
public class Content implements Serializable {
	
	@Id
	String contentid;
	String songname;
	String singer;
	String content_url_rt;
	String content_url_ft;
	String artworkurl;
	String isrc;
	String song_tags;
	String charge_class;
	String subtype;
	String parental_advisory;
	String song_teaser;
	String song_trivia;
	String labelname;
	String marketing_label;
	int cut_allowed;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date startdate;
	@JsonSerialize(using = JsonDateSerializer.class)
	Date enddate;
	
	public Content() {
		super();
	}
	
	public Content(String contentid, String songname, String singer,
			String content_url_rt, String content_url_ft, String artworkurl,
			String isrc, String song_tags, String charge_class, String subtype,
			String parental_advisory, String song_teaser, String song_trivia, String labelname, String marketing_label,
			int cut_allowed, Date startdate, Date enddate) {
		super();
		this.contentid = contentid;
		this.songname = songname;
		this.singer = singer;
		this.content_url_rt = content_url_rt;
		this.content_url_ft = content_url_ft;
		this.artworkurl = artworkurl;
		this.isrc = isrc;
		this.song_tags = song_tags;
		this.charge_class = charge_class;
		this.subtype = subtype;
		this.parental_advisory = parental_advisory;
		this.song_teaser = song_teaser;
		this.song_trivia = song_trivia;
		this.cut_allowed = cut_allowed;
		this.startdate = startdate;
		this.enddate = enddate;
		this.labelname = labelname;
		this.marketing_label = marketing_label;
		
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getContent_url_rt() {
		return content_url_rt;
	}

	public void setContent_url_rt(String content_url_rt) {
		this.content_url_rt = content_url_rt;
	}

	public String getContent_url_ft() {
		return content_url_ft;
	}

	public void setContent_url_ft(String content_url_ft) {
		this.content_url_ft = content_url_ft;
	}

	public String getArtworkurl() {
		return artworkurl;
	}

	public void setArtworkurl(String artworkurl) {
		this.artworkurl = artworkurl;
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	public String getSong_tags() {
		return song_tags;
	}

	public void setSong_tags(String song_tags) {
		this.song_tags = song_tags;
	}

	public String getCharge_class() {
		return charge_class;
	}

	public void setCharge_class(String charge_class) {
		this.charge_class = charge_class;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getParental_advisory() {
		return parental_advisory;
	}

	public void setParental_advisory(String parental_advisory) {
		this.parental_advisory = parental_advisory;
	}

	public String getSong_teaser() {
		return song_teaser;
	}

	public void setSong_teaser(String song_teaser) {
		this.song_teaser = song_teaser;
	}

	public String getSong_trivia() {
		return song_trivia;
	}

	public void setSong_trivia(String song_trivia) {
		this.song_trivia = song_trivia;
	}

	public int getCut_allowed() {
		return cut_allowed;
	}

	public void setCut_allowed(int cut_allowed) {
		this.cut_allowed = cut_allowed;
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

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public String getMarketing_label() {
		return marketing_label;
	}

	public void setMarketing_label(String marketing_label) {
		this.marketing_label = marketing_label;
	}

	public JSONObject toJsonChannelMap(ChannelMap channelMap){
		JSONObject json = new JSONObject();
		json.put("contentid",contentid);
		json.put("songname",songname);
		json.put("singer",singer);
		json.put("content_url_rt",content_url_rt);
		json.put("content_url_ft",content_url_ft);
		json.put("artworkurl",artworkurl);
		json.put("isrc",isrc);
		json.put("song_tags",song_tags);
		json.put("charge_class",charge_class);
		json.put("subtype",subtype);
		json.put("parental_advisory",parental_advisory);
		json.put("cut_allowed",cut_allowed);
		json.put("song_teaser", song_teaser);
		json.put("song_trivia", song_trivia);
		json.put("label_name", labelname);
		json.put("marketing_label", marketing_label);
		json.put("contentorder",channelMap.getContentorder());
		json.put("context_start_date",channelMap.getContext_start_date());
		json.put("context_end_date",channelMap.getContext_end_date());
		return json;
	}
	
	public JSONObject toJsonDownloadHistory(DownloadedTracks track){
		JSONObject json = new JSONObject();
		json.put("contentid",contentid);
		json.put("songname",songname);
		json.put("singer",singer);
		json.put("content_url_rt",content_url_rt);
		json.put("content_url_ft",content_url_ft);
		json.put("artworkurl",artworkurl);
		json.put("isrc",isrc);
		json.put("song_tags",song_tags);
		json.put("charge_class",charge_class);
		json.put("subtype",subtype);
		json.put("parental_advisory",parental_advisory);
		json.put("cut_allowed",cut_allowed);
		json.put("song_teaser", song_teaser);
		json.put("song_trivia", song_trivia); 
		json.put("label_name", labelname);
		json.put("marketing_label", marketing_label);
		json.put("cut_start_time",track.getCut_start_time());
		json.put("cut_end_time",track.getCut_end_time());
		json.put("subscription_id",track.getSubscription_id());
		json.put("downloadedtime",track.getDownloadedtime());
		return json;
	}

	public JSONObject toJson(){
		JSONObject json = new JSONObject();
		json.put("contentid",contentid);
		json.put("songname",songname);
		json.put("singer",singer);
		json.put("content_url_rt",content_url_rt);
		json.put("content_url_ft",content_url_ft);
		json.put("artworkurl",artworkurl);
		json.put("isrc",isrc);
		json.put("song_tags",song_tags);
		json.put("charge_class",charge_class);
		json.put("subtype",subtype);
		json.put("parental_advisory",parental_advisory);
		json.put("cut_allowed",cut_allowed);
		json.put("song_teaser", song_teaser);
		json.put("song_trivia", song_trivia);
		json.put("label_name", labelname);
		json.put("marketing_label", marketing_label);
		return json;
	}
}
