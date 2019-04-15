package com.lunarllc.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunarllc.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//that's it, no need to write any code!
	
	//add method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
	//spring data JPA "magic"
	//www.luv2code.com/query-methods
}
