package com.microservice.learning.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	@Retry(name="sample-api", fallbackMethod = "hardcodedresponse")
	@CircuitBreaker(name="default", fallbackMethod = "hardcodedresponse")
	@RateLimiter(name="default") //in 10s--> allow only 10000 call
	@Bulkhead(name="default")
	public String sampleApi() {
		logger.info("sample api call received");
	//	ResponseEntity<String> entiry=new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
		
		return "sample api call received";
	}
	//exception class extends thwoable
	public String hardcodedresponse(Exception exception) {
		
		return "fallback-response";
	}
}
