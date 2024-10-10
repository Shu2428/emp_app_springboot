package org.jsp.emp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.dao.EducationDao;
import org.jsp.emp.dao.EmployeeDao;
import org.jsp.emp.entity.Address;
import org.jsp.emp.entity.Education;
import org.jsp.emp.entity.Employee;
import org.jsp.emp.exceptionclasses.InvalidCredentialsException;
import org.jsp.emp.exceptionclasses.NoAddressFoundException;
import org.jsp.emp.exceptionclasses.NoEducationFoundException;
import org.jsp.emp.exceptionclasses.NoEmployeeFoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.jsp.emp.util.EducationStatus;
import org.jsp.emp.util.HighestQualification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EducationService 
{
	@Autowired
	private EducationDao eddao;
	
	@Autowired
	private EmployeeDao emdao;
	
	public ResponseEntity<ResponseStructure<?>> saveEducation(int eid,Education education) 
	{
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		Employee foundEmp = optional1.get();
		
//		 education.setStatus(EducationStatus.);
         //employee = dao.saveEmployee(employee); //Already Initialized
		if(education.getHighestQualification()==null || (
				education.getHighestQualification()!=HighestQualification.POSTGRADUATE||
				education.getHighestQualification()!=HighestQualification.UNDERGRADUATE
				)) {
			education.setHighestQualification(HighestQualification.UNKNOWN);
		}
		education.setEmployee(foundEmp);
		Education dbEducation= eddao.saveEducation(education);
		 return  ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder().status(HttpStatus.CREATED.value()).message("Education Saved Successfully").body(dbEducation).build());
	}

	public ResponseEntity<ResponseStructure<?>> findAllEducations(int eid) 
	{
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		List<Education> edl = eddao.findAllEducationsById(eid);
		ResponseStructure<List<Education>> structure = new ResponseStructure<>();
		if(edl.isEmpty())
		{
			throw new NoEducationFoundException("No Active Education Present In The Database Table"); //Exception Handling Layers
		}
	
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Educations Found Successfully");
		structure.setBody(edl); //Only Active Employees Will Come
		
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<?>> findEducationById(int eid,int edid) {
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		
		Employee foundEmployee = optional1.get();
		
		Optional<Education> optional2 =  eddao.findEducationById(eid,edid);
		
		ResponseStructure<Education> structure = new ResponseStructure<>();
		
		if(optional2.isEmpty())
		{
			throw NoEducationFoundException.builder().message("InValid Education Id").build();
		}
		Education e = optional2.get();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Education Found Successfully");
		structure.setBody(e);
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	
	}
	
//
//	public ResponseStructure<List<Education>> findEducationByName(String name) 
//	{
//        ResponseStructure<List<Education>> structure = new ResponseStructure<>(); 
//		
//		List<Education> em =  eddao.findEducationByName(name);
//		if(em.isEmpty())
//		throw new NoEducationFoundException("No Matching Educations Found For The Requested Name");
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setMessage("Educations Found Successfully");
//		structure.setBody(em);
//		return structure;
//	}

	

	public ResponseEntity<ResponseStructure<?>>  deleteEducationById(int eid,int edid)
	{
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty())
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		
		Optional<Education> optional2 =  eddao.findEducationById(eid,edid);
		ResponseStructure<String> structure = new ResponseStructure<>(); 
		if(optional2.isEmpty())
		  throw new NoEducationFoundException("Invalid Education Id Unable To Delete");
		
		eddao.deleteEducationById(eid,edid);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Found And Deleted");
		structure.setBody("Employee Deleted Successfully");
		return ResponseEntity.status(HttpStatus.OK)
				.body(structure);
	}

	public ResponseEntity<ResponseStructure<?>> updateEducation(int eid, int edid, Education education) {
		
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		
		Employee foundEmp = optional1.get();
		Optional<Education> optional2 = eddao.findEducationById(eid, edid);
		if(optional2.isEmpty()) {
			throw NoEducationFoundException.builder().message("No Education Found to update...").build();
		}
		Education oldEducation= optional2.get();
		oldEducation.setPercentage(education.getPercentage());
		oldEducation.setQualification(education.getQualification());
		oldEducation.setCompletionYear(education.getCompletionYear());
		oldEducation.setUniversityName(education.getUniversityName());
		oldEducation.setHighestQualification(education.getHighestQualification());
		oldEducation.setEmployee(education.getEmployee());
		oldEducation.setQualification(education.getQualification());
		
		Education updatedEducation =eddao.saveEducation(oldEducation);
		 return  ResponseEntity.status(HttpStatus.OK).
				 body(ResponseStructure.builder()
						 .status(HttpStatus.OK.value())
						 .message("Address Saved Successfully")
						 .body(updatedEducation).build());
	
		
	}

	public ResponseEntity<ResponseStructure<?>> setEducationHighQualification(int  eid,int edid,String qualification) 
	{
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		
		Optional<Education> optional2 = eddao.findEducationById(eid,edid);
		ResponseStructure<Education> structure = new ResponseStructure<>();
		if(optional2.isEmpty())
			throw new NoEducationFoundException("Invalid Education Id Unable To Set Status To Active");
		Education foundEducation = optional2.get();
		if("postgraduate".equalsIgnoreCase(qualification)) {
			foundEducation.setHighestQualification(HighestQualification.POSTGRADUATE);
		}
		else if("undergraduate".equalsIgnoreCase(qualification)) {
			foundEducation.setHighestQualification(HighestQualification.POSTGRADUATE);
			
		}
		else {
			foundEducation.setHighestQualification(HighestQualification.UNKNOWN);
			
		}
		Education patchedEducation = eddao.saveEducation(foundEducation);
//		e.setStatus(EducationStatus.ACTIVE);
//		e = dao.updateEducation(e);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Education Status Updated To Active Successfully");
		structure.setBody(patchedEducation);
		return ResponseEntity.status(HttpStatus.OK).
				 body(structure);
	}
//
//	public ResponseStructure<Education> setEducationStatusToInActive(int id) 
//	{
//		Optional<Education> optional = dao.findEducationById(id);
//		ResponseStructure<Education> structure = new ResponseStructure<>();
//		if(optional.isEmpty())
//		throw new InvalidEducationIdException("Invalid Education Id Unable To Set Status To In_Active");
//		Education e = optional.get();
//		e.setStatus(EducationStatus.IN_ACTIVE);
//		e = dao.updateEducation(e);
//		
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setMessage("Education Status Updated To InActive Successfully");
//		structure.setBody(e);
//		return structure;
//	}

}
