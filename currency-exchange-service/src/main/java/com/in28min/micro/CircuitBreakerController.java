package com.in28min.micro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
 
	private Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	@Retry(name="sample-api", fallbackMethod = "hardCodedResponse")//add this name to properties file also
	public String sampleApi() {
		logger.info("Sample api call is receieved at "+System.currentTimeMillis());
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/not-exist",
				String.class);
		return forEntity.getBody();
	}
	
	public String hardCodedResponse(Exception ex) {
		return "falback-response!";
	}
}
