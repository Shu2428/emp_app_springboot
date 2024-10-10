package org.jsp.emp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.entity.Address;
import org.jsp.emp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDao 
{
	@Autowired
	private AddressRepository repository;
	
	public Address saveAddress(Address address) 
	{
		return repository.save(address);
	}



	public Optional<Address> findAddressById(int eid,int aid) 
	{
		return repository.findAddressById(eid,aid);
	}
	
	public List<Address> findAllAddresses(int eid)
	{
		return repository.findAllAddressByEid(eid);
	}



	public void deleteAddressById(int eid,int aid) 
	{
		repository.deleteAddressById(eid,aid);
	}

	public Address updateAddress(Address address) 
	{
		return repository.save(address);
	}



	public Optional<Address> findEmpAddressByStreetAddress(String streetAddress) {
		
		return repository.findEmpAddressByStreetAddress(streetAddress);
	}
	
}
