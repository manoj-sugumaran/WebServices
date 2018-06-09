/**
 * 
 */
package com.shark.ringtone.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author manoj.s
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class Properties implements EnvironmentAware{
	
	private static Environment env;

	
	public static String getPropertyValue(String key){
		
		return env.getProperty(key);
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.springframework.core.env.Environment)
	 */
	@Override
	public void setEnvironment(final Environment environment) {
		this.env = environment;
		
	}
}