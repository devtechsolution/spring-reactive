package com.spring.reactive.data.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {

	@Id
	private Integer id;
	private String name;
	private String email;
	private String designation;
}
