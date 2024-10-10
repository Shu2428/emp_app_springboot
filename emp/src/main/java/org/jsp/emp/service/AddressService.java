package org.jsp.emp.service;
import java.util.List;
import java.util.Optional;
import org.jsp.emp.dao.AddressDao;
import org.jsp.emp.dao.EmployeeDao;
import org.jsp.emp.entity.Address;
import org.jsp.emp.entity.Employee;
import org.jsp.emp.exceptionclasses.NoAddressFoundException;
import org.jsp.emp.exceptionclasses.NoEmployeeFoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressService 
{
	@Autowired
	private AddressDao adao;
	
	@Autowired
	private EmployeeDao emdao;
	
	public ResponseEntity<ResponseStructure<?>> saveAddress(int eid, Address address) 
	{

		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		Employee foundEmp = optional1.get();
		address.setEmployee(foundEmp);
		Address savedAddress=adao.saveAddress(address);
		 return  ResponseEntity.status(HttpStatus.CREATED)
				 .body(ResponseStructure.builder()
						 .status(HttpStatus.CREATED.value())
						 .message("Address Saved Successfully")
						 .body(savedAddress).build());
	}

	public ResponseEntity<ResponseStructure<?>> findAllAddresses(int eid) 
	{
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		List<Address> al = adao.findAllAddresses(eid);
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		if(al.isEmpty())
		{
			throw new NoAddressFoundException("No Active Address Present In The Database Table"); //Exception Handling Layers
		}
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Addresses Found Successfully");
		structure.setBody(al); 
		
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<?>> findAddressById(int eid, int aid)
	{
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		
		Employee foundEmployee = optional1.get();
		
		
		Optional<Address> optional2 =  adao.findAddressById(foundEmployee.getId(),aid);
		if(optional2.isEmpty())
		{
			throw NoAddressFoundException.builder().message("InValid Address Id").build();
		}
		Address a = optional2.get();
		ResponseStructure<Address> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Address Found Successfully");
		structure.setBody(a);
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}




	public ResponseEntity<ResponseStructure<String>> deleteAddressById(int eid,int aid)
	{
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty())
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		
	
		
		Optional<Address> optional2 =  adao.findAddressById(eid,aid);
		ResponseStructure<String> structure = new ResponseStructure<>(); 
		if(optional2.isEmpty())
		  throw new NoAddressFoundException("Invalid Address Id Unable To Delete");
		
		adao.deleteAddressById(eid,aid);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Address Found And Deleted");
		structure.setBody("Address Deleted Successfully");
		return ResponseEntity.status(HttpStatus.OK)
				.body(structure);
	}

	public ResponseEntity<ResponseStructure<Object>> updateAddress(int eid, int aid, Address address) {
		// TODO Auto-generated method stub
		Optional<Employee> optional1 = emdao.findEmployeeById(eid);
		if(optional1.isEmpty()) {
			throw NoEmployeeFoundException.builder().message("No Employeee Found ...").build();
		}
		
		Employee foundEmp = optional1.get();
//		Optional<Address> optional2 = adao.findEmpAddressByStreetAddress(address.getStreetAddress());
		Optional<Address> optional2 = adao.findAddressById(eid, aid);
		if(optional2.isEmpty()) {
			throw NoAddressFoundException.builder().message("No Address Found to update...").build();
		}
		Address oldAddress= optional2.get();
		oldAddress.setStreetAddress(address.getStreetAddress());
		oldAddress.setAddressType(address.getAddressType());
		oldAddress.setCity(address.getCity());
		oldAddress.setCountry(address.getCountry());
		oldAddress.setDoorNo(address.getDoorNo());
		oldAddress.setEmployee(address.getEmployee());
		oldAddress.setPostalCode(address.getPostalCode());
		oldAddress.setState(address.getState());
		oldAddress.setAddressLine2(address.getAddressLine2());
		Address updatedAddress =adao.saveAddress(oldAddress);
		 return  ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder().status(HttpStatus.CREATED.value()).message("Address Saved Successfully").body(updatedAddress).build());
	
	}

	


}
