package com.spring.reactive.websocket.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import com.spring.reactive.websocket.dto.GreetingRequest;
import com.spring.reactive.websocket.dto.GreetingResponse;
import com.spring.reactive.websocket.service.GreetingService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
class GreetingWebSocketConfiguration {

  @Bean
  SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler wsh) {
    return new SimpleUrlHandlerMapping(Map.of("/ws/greetings", wsh), 10);
  }

  @Bean
  WebSocketHandler webSocketHandler(GreetingService greetingService) {
    return session -> {
      var receive = session
          .receive()
          .map(WebSocketMessage::getPayloadAsText)
          .map(GreetingRequest::new)
          .flatMap(greetingService::greetMany)
          .map(GreetingResponse::getMessage)
          .map(session::textMessage)
          .doOnEach(signal -> log.info(signal.getType()))
          .doFinally(signal -> log.info("finally: " + signal.toString()));
      return session.send(receive);
    };
  }

  @Bean
  WebSocketHandlerAdapter webSocketHandlerAdapter() {
    return new WebSocketHandlerAdapter();
  }

}
