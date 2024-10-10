package org.jsp.emp.exceptionhandler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.jsp.emp.exceptionclasses.InvalidCredentialsException;
import org.jsp.emp.exceptionclasses.NoActiveEmployeeFoundException;
import org.jsp.emp.exceptionclasses.NoEmployeeFoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //To Redirect The Exceptions In The Class
public class EmployeeExceptionHandler 
{
	@ExceptionHandler(NoActiveEmployeeFoundException.class)
	public ResponseEntity<ResponseStructure<String>>  noActiveEmployeeFoundExceptionHandler(NoActiveEmployeeFoundException e)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No Active Employee Found");
		structure.setBody(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> invalidCredentialsExceptionHandler (InvalidCredentialsException e)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Invalid Email or Password Please Check It");
		structure.setBody(e.getMessage());
		return new ResponseEntity(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoEmployeeFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noEmployeeFoundExceptionHandler(NoEmployeeFoundException e)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No Employee Found");
		structure.setBody(e.getMessage());
		return new ResponseEntity(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Invalid Email Or Phone Number");
		structure.setBody(e.getMessage());
		return new ResponseEntity(structure, HttpStatus.BAD_REQUEST);
	}
}
