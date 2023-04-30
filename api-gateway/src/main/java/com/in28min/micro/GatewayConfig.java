package com.in28min.micro;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
						
		return builder.routes()
				.route(p -> p.path("/get")
						   .filters(f -> f.addRequestHeader("MyHeader", "MyURL")
							   		  .addRequestParameter("MyParam", "MyValue"))
					   .uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/look-how-itis-redirected/**")
						.filters(f -> f.rewritePath(
								"/look-how-itis-redirected/(?<segment>.*)",//segment appends the remaining of url
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion"))
				.build();
	}
}
