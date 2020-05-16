package com.spring.reactive.data.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.Assert;

import com.spring.reactive.data.entity.Employee;
import com.spring.reactive.data.repositories.EmployeeRepositories;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@Data
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl  implements EmployeeService{

	private final EmployeeRepositories employeeRepositories;
	private final TransactionalOperator transactionalOperator;
	
	@Override
	@Transactional
	public Flux<Employee> saveAll(List<Employee> employees) {
		
		Flux<Employee> emps= Flux.fromIterable(employees)
				//.flatMap(e->this.employeeRepositories.save(e));
				.flatMap(this.employeeRepositories::save)
				.doOnNext(this::assertValid);
				
		
		 return emps;
		//return this.transactionalOperator.transactional(emps);
	}
	
	private void assertValid(Employee employee) {
		Assert.isTrue(employee.getName()!=null);
	}

}
