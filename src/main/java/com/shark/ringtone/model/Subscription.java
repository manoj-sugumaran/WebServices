/**
 * 
 */
package com.shark.ringtone.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.minidev.json.JSONObject;

/**
 * @author manoj.s
 *
 */
@Entity
@Cacheable
@Table(name = "subscriptions")
public class Subscription implements Serializable{
	
	@Id
	String subscription_id;
	String name;
	String price;
	int billing_period_days;
	int free_trial_days;
	int download_limit;
	public Subscription() {
		super();
	}
	public Subscription(String subscription_id, String name, String price,
			int billing_period_days, int free_trial_days, int download_limit) {
		super();
		this.subscription_id = subscription_id;
		this.name = name;
		this.price = price;
		this.billing_period_days = billing_period_days;
		this.free_trial_days = free_trial_days;
		this.download_limit = download_limit;
	}
	public String getSubscription_id() {
		return subscription_id;
	}
	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getBilling_period_days() {
		return billing_period_days;
	}
	public void setBilling_period_days(int billing_period_days) {
		this.billing_period_days = billing_period_days;
	}
	public int getFree_trial_days() {
		return free_trial_days;
	}
	public void setFree_trial_days(int free_trial_days) {
		this.free_trial_days = free_trial_days;
	}
	public int getDownload_limit() {
		return download_limit;
	}
	public void setDownload_limit(int download_limit) {
		this.download_limit = download_limit;
	}
	@Override
	public String toString() {
		return "Subscriptions [subscription_id=" + subscription_id + ", name="
				+ name + ", price=" + price + ", billing_period_days="
				+ billing_period_days + ", free_trial_days=" + free_trial_days
				+ ", download_limit=" + download_limit + "]";
	}

}
