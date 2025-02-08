package com.microservice.learning.currency_exchange_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long>{

   CurrencyExchange	findByFromAndTo(String from, String to);
}
