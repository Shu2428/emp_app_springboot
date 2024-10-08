package org.jsp.emp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer>
{
//	@Query("Select a from Address a where a.status=AddressStatus.ACTIVE")
//	List<Address> findAllActiveAddresses();

	List<Address> findByName(String name);

//	Optional<Address> findByEmailAndPassword(String email, String password);

	List<Address> findByStreetAddress(String streetAddress);
	
}
