package com.shark.ringtone.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
/**
 * @author manoj.s
 *
 */
@Embeddable
public class ChannelMapKey implements Serializable{
	
	@NotNull
    private String channelid;

    @NotNull
    private String contentid;

	public ChannelMapKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChannelMapKey(@NotNull String channelid, @NotNull String contentid) {
		super();
		this.channelid = channelid;
		this.contentid = contentid;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	@Override
	public String toString() {
		return "ChannelMapKey [channelid=" + channelid + ", contentid=" + contentid + "]";
	}
    
    

}
