package com.spring.reactive.websocket.service;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.spring.reactive.websocket.dto.GreetingRequest;
import com.spring.reactive.websocket.dto.GreetingResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class GreetingServiceImpl implements GreetingService {

	@Override
	public Flux<GreetingResponse> greetMany(GreetingRequest request) {
		return Flux.fromStream(Stream.generate(() -> greet(request.getName()))).delayElements(Duration.ofSeconds(1))
				.subscribeOn(Schedulers.elastic());
	}

	@Override
	public Mono<GreetingResponse> greetOnce(GreetingRequest request) {
		return Mono.just(greet(request.getName()));
	}

	private GreetingResponse greet(String name) {
		return new GreetingResponse("Hello " + name + " @ " + Instant.now());
	}

}
