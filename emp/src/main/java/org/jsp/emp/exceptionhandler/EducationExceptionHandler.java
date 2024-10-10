package org.jsp.emp.exceptionhandler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.jsp.emp.exceptionclasses.NoEducationFoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EducationExceptionHandler 
{
	
	
	
	
	@ExceptionHandler(NoEducationFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noEducationFoundExceptionHandler(NoEducationFoundException e)
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
