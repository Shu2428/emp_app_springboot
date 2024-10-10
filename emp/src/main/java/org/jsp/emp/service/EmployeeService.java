package org.jsp.emp.service;


import java.util.List;
import java.util.Optional;

import org.jsp.emp.dao.EmployeeDao;
import org.jsp.emp.entity.Employee;
import org.jsp.emp.exceptionclasses.InvalidCredentialsException;
import org.jsp.emp.exceptionclasses.NoActiveEmployeeFoundException;
import org.jsp.emp.exceptionclasses.NoEmployeeFoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.jsp.emp.util.EmployeeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService
{
	@Autowired
	private EmployeeDao dao;
	
	public ResponseEntity<?> saveEmployee(Employee employee)
	{
		 employee.setStatus(EmployeeStatus.IN_ACTIVE);
         //employee = dao.saveEmployee(employee); //Already Initialized
		
		 return  ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder().status(HttpStatus.CREATED.value()).message("Employee Saved Successfully").body(dao.saveEmployee(employee)).build());
	}
	public ResponseEntity<?> updateEmployee(Employee employee) 
	{
		//employee = dao.updateEmployee(employee);
		
		 
		 return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee Updated Successfully").body(dao.updateEmployee(employee)).build());
	}
	
	public ResponseStructure<Employee> findEmployeeById(int id) 
	{
		
		Optional<Employee> optional =  dao.findEmployeeById(id);
		
		ResponseStructure<Employee> structure = new ResponseStructure<>();
		
		if(optional.isEmpty())
		{
			throw NoEmployeeFoundException.builder().message("InValid Employee Id").build();
		}
		Employee e = optional.get();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Found Successfully");
		structure.setBody(e);
		return structure;
	}
	
	public ResponseStructure<List<Employee>> findAllEmployees()
	{
		List<Employee> el = dao.findAllActiveEmployees();
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		if(el.isEmpty())
		{
			throw NoActiveEmployeeFoundException.builder().message("No Active Employee Present In The Database Table").build(); //Exception Handling Layers
		}

		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Employees Found Successfully");
		structure.setBody(el); //Only Active Employees Will Come
		
		return structure;
	}
	
	public ResponseStructure<String> deleteEmployeeById(int id) 
	{
		Optional<Employee> optional =  dao.findEmployeeById(id);
		ResponseStructure<String> structure = new ResponseStructure<>(); 
		if(optional.isEmpty())
		  throw NoEmployeeFoundException.builder().message("Invalid Employee Id Unable To Delete").build();
		
		dao.deleteEmployeeById(id);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Found And Deleted");
		structure.setBody("Employee Deleted Successfully");
		return structure;
	}
	
	public ResponseStructure<Employee> findEmployeeByEmailAndPassword(String email, String password)
	{
		ResponseStructure<Employee> structure = new ResponseStructure<>(); 
		
		
		Optional<Employee> optional =  dao.findEmployeeByEmailAndPassword(email, password);
		if(optional.isEmpty())
		  throw new InvalidCredentialsException("Invalid Email Or Password");
		
	    Employee e = optional.get();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Verficiation Successfull");
	    structure.setBody(e);
		return structure;
	}
	
	public ResponseStructure<List<Employee>> findEmployeeByName(String name)
	{
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>(); 
		
		List<Employee> em =  dao.findEmployeeByName(name);
		if(em.isEmpty())
		throw  NoEmployeeFoundException.builder().message("No Matching Employees Found For The Requested Name").build();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employees Found Successfully");
		structure.setBody(em);
		return structure;
	}

	public ResponseStructure<Employee> setEmployeeStatusToActive(int id) 
	{
		Optional<Employee> optional = dao.findEmployeeById(id);
		ResponseStructure<Employee> structure = new ResponseStructure<>();
		if(optional.isEmpty())
			throw NoEmployeeFoundException.builder().message("Invalid Employee Id Unable To Set Status To Active").build();
		Employee e = optional.get();
		e.setStatus(EmployeeStatus.ACTIVE);
		e = dao.updateEmployee(e);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Status Updated To Active Successfully");
		structure.setBody(e);
		return structure;
	}

	public ResponseStructure<Employee> setEmployeeStatusToInActive(int id) 
	{
		Optional<Employee> optional = dao.findEmployeeById(id);
		ResponseStructure<Employee> structure = new ResponseStructure<>();
		if(optional.isEmpty())
		throw NoEmployeeFoundException.builder().message("Invalid Employee Id Unable To Set Status To In_Active").build();
		Employee e = optional.get();
		e.setStatus(EmployeeStatus.IN_ACTIVE);
		e = dao.updateEmployee(e);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Status Updated To InActive Successfully");
		structure.setBody(e);
		return structure;
	}
	
}
