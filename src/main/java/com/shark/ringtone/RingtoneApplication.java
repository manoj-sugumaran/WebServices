package com.shark.ringtone;

import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.shark.ringtone.utils.Properties;


/**
 * @author manoj.s
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class RingtoneApplication extends SpringBootServletInitializer {
	private final static Logger log = Logger.getLogger(RingtoneApplication.class);
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RingtoneApplication.class);
    }
	
	@PostConstruct
	void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}
	
	public static void main(String[] args) throws IOException {
		
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
		SpringApplication.run(RingtoneApplication.class, args);
	}
}
