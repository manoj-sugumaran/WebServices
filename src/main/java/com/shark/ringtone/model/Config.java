package com.shark.ringtone.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author manoj.s
 *
 */
@Entity
@Table(name = "appconfig")
public class Config implements Serializable{
	
	@Id
	String module;
	String name;
	String value;
	String os_type;
	
	public Config() {
		super();
	}
	
	public Config(String module, String name, String value, String os_type) {
		super();
		this.module = module;
		this.name = name;
		this.value = value;
		this.os_type = os_type;
	}

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOs_type() {
		return os_type;
	}
	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}
	
	@Override
	public String toString() {
		return "Config [module=" + module + ", name=" + name + ", value=" + value + ", os_type=" + os_type + "]";
	}
}
