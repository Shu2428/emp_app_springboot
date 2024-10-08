package org.jsp.emp.controller;

import java.util.List;

import org.jsp.emp.entity.Education;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.jsp.emp.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educations")
public class EducationController 
{
	@Autowired
	private EducationService service;
	
	@PostMapping
	public ResponseEntity<?> saveEducation(@RequestBody Education education) //structure returning thats why we are using this
	{		 
		return service.saveEducation(education);
	}
	
	@GetMapping
	public ResponseStructure<List<Education>> findAllEducations()
	{
		 return service.findAllEducations();
	}
	
	@GetMapping("/{id}")
	public ResponseStructure<Education> findEducationsById(@PathVariable int id)//Object Changing
	{
		
		return service.findEducationById(id);
	}
	
	@GetMapping("/name/{name}")
	public ResponseStructure<List<Education>> findEducationByName(@PathVariable String name)
	{
		return service.findEducationByName(name);
	}
	
	@GetMapping("/{email}/{password}")
	public ResponseStructure<Education> findEducationByEmailAndPassword(@PathVariable String email, @PathVariable String password)
	{
		return service.findEducationByEmailAndPassword(email, password);
	}
	
	@DeleteMapping("/{id}")
	public ResponseStructure<String> deleteEducationById(@PathVariable int id)
	{
		return service.deleteEducationById(id);
	}
	
	@PutMapping
	public ResponseEntity<?> updateEducation(@RequestBody Education education) 
	{
		 return service.updateEducation(education); 
	}
	
	@PatchMapping("/active/{id}")
	public ResponseStructure<Education> setEducationStatusToActive(@PathVariable int id)
	{
		return service.setEducationStatusToActive(id);
	}
	
	@PatchMapping("/inactive/{id}")
	public ResponseStructure<Education> setEducationStatusToInActive(@PathVariable int id)
	{
		return service.setEducationStatusToInActive(id);
	}
}
