package com.batch.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Upendra
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

	private String empId;

	private String firstName;

	private String middleInitial;

	private String lastName;

	private String gender;

	private String email;

	private String fatherName;

	private String motherName;

	private String weight;

	private String salary;

	private String phoneNumber;

	private String country;

	private String city;

	private String state;

	private String zipCode;

	private String region;

	private String userName;

	private String password;

}
