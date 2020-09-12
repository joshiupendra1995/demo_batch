package com.batch.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Upendra
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	private Integer empId;

	private String firstName;
	
	private String middleInitial;

	private String lastName;

	private String gender;

	private String email;

	private String fatherName;

	private String motherName;

	private Integer weight;

	private Double salary;

	private String phoneNumber;

	private String country;

	private String city;

	private String state;

	private Integer zipCode;

	private String region;

	private String userName;

	private String password;

}
