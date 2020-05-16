package com.spring.reactive.http.service;

import com.spring.reactive.http.dto.GreetingRequest;
import com.spring.reactive.http.dto.GreetingResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GreetingService {
	
	public Flux<GreetingResponse> greetMany(GreetingRequest request);
	public Mono<GreetingResponse> greetOnce(GreetingRequest request);

}
