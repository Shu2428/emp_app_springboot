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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educations")
public class EducationController 
{
	@Autowired
	private EducationService edservice;
	
	@GetMapping("{eid}")
	public ResponseEntity<ResponseStructure<?>> findAllEducations(@PathVariable int eid)
	{
		 return edservice.findAllEducations(eid);
	}
	
	@GetMapping("/{eid}/{edid}")
	public ResponseEntity<ResponseStructure<?>> findEducationsById(@PathVariable int eid,@PathVariable int edid)//Object Changing
	{
		
		return edservice.findEducationById(eid,edid);
	}
	
	@PostMapping("{eid}")
	public ResponseEntity<ResponseStructure<?>> saveEducation(@PathVariable int eid,@RequestBody Education education) //structure returning thats why we are using this
	{		 
		return edservice.saveEducation(eid,education);
	}

	
	
	@DeleteMapping("/{eid}/{edid}")
	public ResponseEntity<ResponseStructure<?>>deleteEducationById(@PathVariable int eid,@PathVariable int edid)
	{
		return edservice.deleteEducationById(eid,edid);
	}
	
	@PutMapping("/{eid}/{edid}")
	public ResponseEntity<ResponseStructure<?>> updateEducation(@PathVariable int eid,@PathVariable int edid,@RequestBody Education education) 
	{
		 return edservice.updateEducation(eid,edid,education); 
	}
	
	@PatchMapping("/active/{eid}/{edid}")
	public ResponseEntity<ResponseStructure<?>> setEducationHighQualification(@PathVariable int eid,@PathVariable int edid,@RequestParam String qualification)
	{
		return edservice.setEducationHighQualification(eid,edid,qualification);
	}
	
	
}
