package com.microservice.learning.currency_exchange_service;

import java.math.BigDecimal;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepo currencyExchangeRepo;
	
	private Logger logger= LoggerFactory.getLogger(CurrencyExchangeController.class);

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@CircuitBreaker(name="currecyExchangeService", fallbackMethod = "currecyExchangeServiceFallback")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from,@PathVariable String to) {
		//[currency-exchange,449d800bb551727685c0d1de8fd7fb77,ceac50ff4ead1e10] 
		logger.info("retriveExchangeValue called with {} to {} ",from,to);
		CurrencyExchange currencyExchange= currencyExchangeRepo.findByFromAndTo(from, to);
		if(currencyExchange== null) {
			throw new RuntimeException("Unable to find data from "+from+ "to "+to);
		}
		currencyExchange.setEnv(environment.getProperty("local.server.port"));
		return currencyExchange;
	}

	public CurrencyExchange currecyExchangeServiceFallback(Throwable t) {
		CurrencyExchange currencyExchange=new CurrencyExchange();
		currencyExchange.setFrom("INR");
		currencyExchange.setTo("CAD");
		currencyExchange.setConvValue(BigDecimal.ONE);
		currencyExchange.setEnv(environment.getProperty("local.server.port"));
		return currencyExchange;
	}
	
}
