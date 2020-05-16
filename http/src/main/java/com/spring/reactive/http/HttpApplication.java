package com.spring.reactive.http;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.reactive.http.dto.GreetingRequest;
import com.spring.reactive.http.dto.GreetingResponse;
import com.spring.reactive.http.service.GreetingService;


@SpringBootApplication
public class HttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpApplication.class, args);
	}
	
	@Bean
	  RouterFunction<ServerResponse> routes(GreetingService gs) {
	    return  		
	    		route()
	    		.GET("/greeting/{name}",
	    				r -> ok().body(gs.greetOnce(new GreetingRequest(r.pathVariable("name"))), GreetingResponse.class))
	    		.GET("/greetings/{name}", 
	    				r -> ok().contentType(MediaType.TEXT_EVENT_STREAM).body(gs.greetMany(new GreetingRequest(r.pathVariable("name"))), GreetingResponse.class))
	        .build();
	  }

}
