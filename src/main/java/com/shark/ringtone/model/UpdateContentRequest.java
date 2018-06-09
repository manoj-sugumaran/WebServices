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
public class UpdateContentRequest {
	
	@NotBlank
	String contentid;
	@NotBlank
	String trivia;
	@NotBlank
	String teaser;
	public UpdateContentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateContentRequest(@NotBlank String contentid,
			@NotBlank String trivia, @NotBlank String teaser) {
		super();
		this.contentid = contentid;
		this.trivia = trivia;
		this.teaser = teaser;
	}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public String getTrivia() {
		return trivia;
	}
	public void setTrivia(String trivia) {
		this.trivia = trivia;
	}
	public String getTeaser() {
		return teaser;
	}
	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}
	@Override
	public String toString() {
		return "UpdateContentRequest [contentid=" + contentid + ", trivia="
				+ trivia + ", teaser=" + teaser + "]";
	}
	
}
