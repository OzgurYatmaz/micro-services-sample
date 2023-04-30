package com.in28min.micro;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange", url="localhost:8000")//manual without naming server
@FeignClient(name="currency-exchange")//url is auto takken from eureka
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangedValue(
			@PathVariable String from, @PathVariable String to);
		
		
	
}
