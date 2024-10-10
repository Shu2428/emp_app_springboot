package org.jsp.emp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Address, Integer>
{
//	@Query("Select a from Address a where a.status=AddressStatus.ACTIVE")
//	List<Address> findAllActiveAddresses();

//	List<Address> findByName(String name);

//	Optional<Address> findByEmailAndPassword(String email, String password);

	List<Address> findByStreetAddress(String streetAddress);

	@Query("select a from Address a where a.employee.id=:eid")
	List<Address> findAllAddressByEid(int eid);

	@Query("select a from Address a where a.employee.id=:eid and a.id=:aid")
	Optional<Address> findAddressById(int eid, int aid);

	@Modifying
	@Transactional
	@Query("delete from Address a where a.employee.id=:eid and a.id=:aid")
	void deleteAddressById(int eid, int aid);

	@Query("select a from Address a where a.streetAddress=:streetad")
	Optional<Address> findEmpAddressByStreetAddress(String streetad);
	
}
