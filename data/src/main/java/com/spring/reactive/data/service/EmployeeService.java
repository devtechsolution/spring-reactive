package com.spring.reactive.data.service;

import java.util.List;

import com.spring.reactive.data.entity.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeService {
	
	Flux<Employee> saveAll(List<Employee> employees);

}
