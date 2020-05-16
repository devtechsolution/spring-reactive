package com.spring.reactive.websocket.service;

import com.spring.reactive.websocket.dto.GreetingRequest;
import com.spring.reactive.websocket.dto.GreetingResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GreetingService {
	
	public Flux<GreetingResponse> greetMany(GreetingRequest request);
	public Mono<GreetingResponse> greetOnce(GreetingRequest request);

}
