package com.batch.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.batch.demo.entity.Employee;
import com.batch.demo.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@CachePut("employee")
	public void saveEmployees(List<? extends Employee> employee) {
		empRepo.saveAll(employee);
	}

}
