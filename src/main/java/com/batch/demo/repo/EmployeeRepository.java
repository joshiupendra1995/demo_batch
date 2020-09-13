package com.batch.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch.demo.entity.Employee;

/**
 * @author Upendra
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
