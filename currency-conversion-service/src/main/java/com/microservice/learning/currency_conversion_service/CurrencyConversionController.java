package com.microservice.learning.currency_conversion_service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		HashMap<String, String> uriVariables= new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion>	conversion=new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class, uriVariables);
		CurrencyConversion currencyConversion=conversion.getBody();
		return new CurrencyConversion(currencyConversion.getId(),from,to,
				quantity,currencyConversion.getConvValue(),
				quantity.multiply(currencyConversion.getConvValue()),currencyConversion.getEnv());
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyFeign(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		CurrencyConversion currencyConversion	=currencyExchangeProxy.retriveExchangeValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(),from,to,
				quantity,currencyConversion.getConvValue(),
				quantity.multiply(currencyConversion.getConvValue()),currencyConversion.getEnv());
	}
	
		
}
