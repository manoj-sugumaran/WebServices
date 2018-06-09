package com.shark.ringtone.controller;

import java.util.List;

import net.minidev.json.JSONObject;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shark.ringtone.model.Config;
import com.shark.ringtone.repository.ConfigRepository;
/**
 * @author manoj.s
 *
 */
@RestController
public class ConfigController {
	private final static Logger log = Logger.getLogger(ConfigController.class);

	@Autowired
    ConfigRepository configRepository;
	
	@GetMapping(path = "/GetConfig")
	public List<Config> getConfig(){
		
		log.info("Inside GetConfig API \n Fetching all config");
		return configRepository.findAll();
	}
	
	@PostMapping(path = "/SaveConfig")
	public ResponseEntity<JSONObject> saveConfig(@RequestBody Config config){
		
		JSONObject response = new JSONObject();
		log.info("Inserting or Updating config with name :"+config.getName());
		configRepository.save(config);
		response.put("status", "success");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(path = "/DeleteConfig/{module}")
	public String deleteConfig(@PathVariable String module){
		
		log.info("Config to be deleted with module name :"+module);
		try{
			configRepository.deleteById(module);
		}catch(Exception e){
			return "Error";
		}
		
		return "Success";
	}
}
