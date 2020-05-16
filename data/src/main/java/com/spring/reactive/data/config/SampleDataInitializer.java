package com.spring.reactive.data.config;

import java.util.Arrays;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import com.spring.reactive.data.entity.Employee;
import com.spring.reactive.data.repositories.EmployeeRepositories;
import com.spring.reactive.data.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Log4j2
public class SampleDataInitializer {
	
	private final EmployeeRepositories employeeRepositories;
	private final DatabaseClient databaseClient;
	private final EmployeeService employeeService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void ready() {
		
	this.databaseClient
		.select()
		.from("employee")
		.as(Employee.class)
		.fetch()
		.all()
		.doOnComplete(()->log.info("-------------------------"))
		.subscribe(log::info);
	
	Flux<Employee> employees= employeeService.saveAll(
			Arrays.asList(
				new Employee(null, "Rama", "rama@gmail.com", "ATA"),
				new Employee(null, "Shiva", "shiva@gmail.com", "STA"),
				new Employee(null, "Bramha", "bramha@gmail.com", "PMO")
			));
	
	this.employeeRepositories
		.deleteAll()
		.thenMany(employees)
		.thenMany(this.employeeRepositories.findAll())
		.subscribe(log::info);
	
	System.out.println("--ddsfsd");
			
		
		
	}
	

}
