package org.jsp.emp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.entity.Education;
import org.jsp.emp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EducationRepository extends JpaRepository<Education, Integer>
{

	Education save(Education education);

	Optional<Education> findById(int id);

	List<Education> findAll();

	@Query("Select e from Education e where e.status='ACTIVE'")
	List<Education> findAllActiveEducations();

	void deleteById(int id);

	Optional<Education> findByEmailAndPassword(String email, String password);

	List<Education> findByName(String name);
	
}
