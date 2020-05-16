package com.spring.reactive.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.spring.reactive.data.entity.Employee;

import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication
public class DataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}
	
	@Bean
	ReactiveTransactionManager r2dbcTransactionManager(ConnectionFactory cf) {
		return new R2dbcTransactionManager(cf);
	}
	
	@Bean
	TransactionalOperator transacrtionalOperator(ReactiveTransactionManager rtm) {
		return TransactionalOperator.create(rtm);
	}

}
