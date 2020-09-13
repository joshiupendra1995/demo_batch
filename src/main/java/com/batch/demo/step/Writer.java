package com.batch.demo.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.batch.demo.entity.Employee;
import com.batch.demo.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Upendra
 *
 */
@Component
@Slf4j
public class Writer implements ItemWriter<Employee> {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public void write(List<? extends Employee> employee) throws Exception {
		
		log.info("Data Saved for Employee: " + employeeService);
		employeeService.saveEmployees(employee);
	}

}
