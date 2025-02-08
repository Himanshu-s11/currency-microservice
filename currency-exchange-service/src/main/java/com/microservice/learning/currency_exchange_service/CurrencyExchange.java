package com.microservice.learning.currency_exchange_service;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CurrencyExchange")
public class CurrencyExchange {

	@Id
	private Long id;
	@Column(name="currency_from")
	private String from;
	@Column(name="currency_to")
	private String to;
	private BigDecimal convValue;
	private String env;

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
	
	public String getEnv() {
		return env;
	}
	
	public void setEnv(String env) {
		this.env=env;
	}
	
	public CurrencyExchange() {
		// TODO Auto-generated constructor stub
	}
	public CurrencyExchange(Long id, String from, String to, BigDecimal convValue) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.convValue = convValue;
	}
	@Override
	public String toString() {
		return "CurrencyExchange [id=" + id + ", from=" + from + ", to=" + to + ", convValue=" + convValue + "]";
	}
	
}
