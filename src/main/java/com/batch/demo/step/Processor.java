package com.batch.demo.step;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.demo.entity.Employee;
import com.batch.demo.model.EmployeeDto;

@Component
public class Processor implements ItemProcessor<EmployeeDto, Employee> {

	@Override
	public Employee process(EmployeeDto emp) throws Exception {
		return new Employee(Integer.valueOf(emp.getEmpId()), emp.getFirstName(), emp.getMiddleInitial(),
				emp.getLastName(), emp.getGender(), emp.getEmail(), emp.getFatherName(), emp.getMotherName(),
				Integer.valueOf(emp.getWeight()), Double.valueOf(emp.getSalary()), emp.getPhoneNumber(),
				emp.getCountry(), emp.getCity(), emp.getState(), Integer.valueOf(emp.getZipCode()), emp.getRegion(),
				emp.getUserName(), emp.getPassword());
	}

}
