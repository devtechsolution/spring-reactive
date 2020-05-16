package com.spring.reactive.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.reactive.http.dto.GreetingRequest;
import com.spring.reactive.http.dto.GreetingResponse;
import com.spring.reactive.http.service.GreetingService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
class GreetingsRestController {

  private final GreetingService greetingService;

  @GetMapping("/greeting/{name}")
  Mono<GreetingResponse> greet(@PathVariable String name) {
    return this.greetingService.greetOnce(new GreetingRequest(name));
  }
}