package com.spring.reactive.data.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.spring.reactive.data.entity.Employee;

public interface EmployeeRepositories extends ReactiveCrudRepository<Employee, Integer> {

}
