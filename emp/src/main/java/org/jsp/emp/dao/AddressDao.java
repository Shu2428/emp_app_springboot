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

	public List<Address> findAllActiveAddress() 
	{
		return repository.findAll();
	}

	public Optional<Address> findAddressById(int id) 
	{
		return repository.findById(id);
	}
	
	public List<Address> findAllAddresses()
	{
		return repository.findAll();
	}

	public List<Address> findAddressByName(String streetAddress) 
	{
		return repository.findByStreetAddress(streetAddress);
	}

//	public Optional<Address> findAddressByEmailAndPassword(String email, String password) 
//	{
//		return repository.findByEmailAndPassword(email,password);
//	}

	public void deleteAddressById(int id) 
	{
		repository.deleteById(id);
	}

	public Address updateAddress(Address address) 
	{
		return repository.save(address);
	}
	
}
