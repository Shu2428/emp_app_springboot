package org.jsp.emp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.dao.AddressDao;
import org.jsp.emp.entity.Address;
import org.jsp.emp.exceptionclasses.InvalidAddressIdException;
import org.jsp.emp.exceptionclasses.InvalidCredentialssException;
import org.jsp.emp.exceptionclasses.NoActiveAddressFoundException;
import org.jsp.emp.exceptionclasses.NoAddressFoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.jsp.emp.util.AddressStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressService 
{
	@Autowired
	private AddressDao dao;
	
	public ResponseEntity<?> saveAddress(Address address) 
	{
//		address.setStatus(AddressStatus.IN_ACTIVE);
		
		 return  ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder().status(HttpStatus.CREATED.value()).message("Address Saved Successfully").body(dao.saveAddress(address)).build());
	}

	public ResponseStructure<List<Address>> findAllAddresses() 
	{
		List<Address> al = dao.findAllActiveAddress();
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		if(al.isEmpty())
		{
			throw new NoActiveAddressFoundException("No Active Address Present In The Database Table"); //Exception Handling Layers
		}
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Addresses Found Successfully");
		structure.setBody(al); 
		
		return structure;
	}

	public ResponseStructure<Address> findAddressById(int id) 
	{
        Optional<Address> optional =  dao.findAddressById(id);
		
		ResponseStructure<Address> structure = new ResponseStructure<>();
		
		if(optional.isEmpty())
		{
			throw InvalidAddressIdException.builder().message("InValid Address Id").build();
		}
		Address a = optional.get();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Address Found Successfully");
		structure.setBody(a);
		return structure;
	}

	public ResponseStructure<List<Address>> findAddressByName(String name) 
	{
        ResponseStructure<List<Address>> structure = new ResponseStructure<>(); 
		
		List<Address> am =  dao.findAddressByName(name);
		if(am.isEmpty())
		throw new NoAddressFoundException("No Matching Addresses Found For The Requested Name");
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Addresses Found Successfully");
		structure.setBody(am);
		return structure;
	}

//	5

	public ResponseStructure<String> deleteAddressById(int id)
	{
		Optional<Address> optional =  dao.findAddressById(id);
		ResponseStructure<String> structure = new ResponseStructure<>(); 
		if(optional.isEmpty())
		  throw new InvalidAddressIdException("Invalid Address Id Unable To Delete");
		
		dao.deleteAddressById(id);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Address Found And Deleted");
		structure.setBody("Address Deleted Successfully");
		return structure;
	}

	public ResponseEntity<?> updateAddress(Address address) 
	{
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee Updated Successfully").body(dao.updateAddress(address)).build());
	}

//	public ResponseStructure<Address> setAddressStatusToActive(int id)
//	{
//		Optional<Address> optional = dao.findAddressById(id);
//		ResponseStructure<Address> structure = new ResponseStructure<>();
//		if(optional.isEmpty())
//			throw new InvalidAddressIdException("Invalid Address Id Unable To Set Status To Active");
//		Address a = optional.get();
////		a.setStatus(AddressStatus.ACTIVE);
//		a = dao.updateAddress(a);
//		
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setMessage("Address Status Updated To Active Successfully");
//		structure.setBody(a);
//		return structure;
//	}

//	public ResponseStructure<Address> setAddressStatusToInActive(int id) 
//	{
//		Optional<Address> optional = dao.findAddressById(id);
//		ResponseStructure<Address> structure = new ResponseStructure<>();
//		if(optional.isEmpty())
//		throw new InvalidAddressIdException("Invalid Address Id Unable To Set Status To In_Active");
//		Address a = optional.get();
//		a.setStatus(AddressStatus.IN_ACTIVE);
//		a = dao.updateAddress(a);
//		
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setMessage("Address Status Updated To InActive Successfully");
//		structure.setBody(a);
//		return structure;
//	}

}
