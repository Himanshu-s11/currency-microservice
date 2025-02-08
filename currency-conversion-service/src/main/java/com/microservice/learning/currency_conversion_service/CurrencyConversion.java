package com.microservice.learning.currency_conversion_service;

import java.math.BigDecimal;


public class CurrencyConversion {

	private Long id;
	private String from;
	private String to;
	private BigDecimal quantity;
	private BigDecimal convValue;
	
	private BigDecimal totalClaculatedAmmount;
	private String env;
	
	public CurrencyConversion() {
		// TODO Auto-generated constructor stub
	}

	public CurrencyConversion(Long id, String from, String to, BigDecimal quantity, BigDecimal convValue,
			BigDecimal totalClaculatedAmmount, String env) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.convValue = convValue;
		this.totalClaculatedAmmount = totalClaculatedAmmount;
		this.env = env;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConvValue() {
		return convValue;
	}

	public void setConvValue(BigDecimal convValue) {
		this.convValue = convValue;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalClaculatedAmmount() {
		return totalClaculatedAmmount;
	}

	public void setTotalClaculatedAmmount(BigDecimal totalClaculatedAmmount) {
		this.totalClaculatedAmmount = totalClaculatedAmmount;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}
	
	
}
