package org.jsp.emp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.dao.EducationDao;
import org.jsp.emp.entity.Education;
import org.jsp.emp.exceptionclasses.InvalidCredentialsException;
import org.jsp.emp.exceptionclasses.InvalidEducationIdException;
import org.jsp.emp.exceptionclasses.InvalidEmployeeIdException;
import org.jsp.emp.exceptionclasses.NoActiveEducationFoundException;
import org.jsp.emp.exceptionclasses.NoEducationFoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.jsp.emp.util.EducationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EducationService 
{
	@Autowired
	private EducationDao dao;
	
	public ResponseEntity<?> saveEducation(Education education) 
	{
		 education.setStatus(EducationStatus.IN_ACTIVE);
         //employee = dao.saveEmployee(employee); //Already Initialized
		
		 return  ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder().status(HttpStatus.CREATED.value()).message("Education Saved Successfully").body(dao.saveEducation(education)).build());
	}

	public ResponseStructure<List<Education>> findAllEducations() 
	{
		List<Education> ed = dao.findAllActiveEducations();
		ResponseStructure<List<Education>> structure = new ResponseStructure<>();
		if(ed.isEmpty())
		{
			throw new NoActiveEducationFoundException("No Active Education Present In The Database Table"); //Exception Handling Layers
		}
	
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Educations Found Successfully");
		structure.setBody(ed); //Only Active Employees Will Come
		
		return structure;
	}

	public ResponseStructure<Education> findEducationById(int id) {
        Optional<Education> optional =  dao.findEducationById(id);
		
		ResponseStructure<Education> structure = new ResponseStructure<>();
		
		if(optional.isEmpty())
		{
			throw InvalidEducationIdException.builder().message("InValid Education Id").build();
		}
		Education e = optional.get();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Education Found Successfully");
		structure.setBody(e);
		return structure;
	}

	public ResponseStructure<List<Education>> findEducationByName(String name) 
	{
        ResponseStructure<List<Education>> structure = new ResponseStructure<>(); 
		
		List<Education> em =  dao.findEducationByName(name);
		if(em.isEmpty())
		throw new NoEducationFoundException("No Matching Educations Found For The Requested Name");
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Educations Found Successfully");
		structure.setBody(em);
		return structure;
	}

	public ResponseStructure<Education> findEducationByEmailAndPassword(String email, String password) 
	{
        ResponseStructure<Education> structure = new ResponseStructure<>(); 
		
		
		Optional<Education> optional =  dao.findEducationByEmailAndPassword(email, password);
		if(optional.isEmpty())
		  throw new InvalidCredentialsException("Invalid Email Or Password");
		
	    Education e = optional.get();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Verficiation Successfull");
	    structure.setBody(e);
		return structure;
	}

	public ResponseStructure<String> deleteEducationById(int id)
	{
		Optional<Education> optional =  dao.findEducationById(id);
		ResponseStructure<String> structure = new ResponseStructure<>(); 
		if(optional.isEmpty())
		  throw new InvalidEducationIdException("Invalid Education Id Unable To Delete");
		
		dao.deleteEducationById(id);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Found And Deleted");
		structure.setBody("Employee Deleted Successfully");
		return structure;
	}

	public ResponseEntity<?> updateEducation(Education education) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Education Updated Successfully").body(dao.updateEducation(education)).build());
	}

	public ResponseStructure<Education> setEducationStatusToActive(int id) 
	{
		Optional<Education> optional = dao.findEducationById(id);
		ResponseStructure<Education> structure = new ResponseStructure<>();
		if(optional.isEmpty())
			throw new InvalidEducationIdException("Invalid Education Id Unable To Set Status To Active");
		Education e = optional.get();
		e.setStatus(EducationStatus.ACTIVE);
		e = dao.updateEducation(e);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Education Status Updated To Active Successfully");
		structure.setBody(e);
		return structure;
	}

	public ResponseStructure<Education> setEducationStatusToInActive(int id) 
	{
		Optional<Education> optional = dao.findEducationById(id);
		ResponseStructure<Education> structure = new ResponseStructure<>();
		if(optional.isEmpty())
		throw new InvalidEducationIdException("Invalid Education Id Unable To Set Status To In_Active");
		Education e = optional.get();
		e.setStatus(EducationStatus.IN_ACTIVE);
		e = dao.updateEducation(e);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Education Status Updated To InActive Successfully");
		structure.setBody(e);
		return structure;
	}

}
