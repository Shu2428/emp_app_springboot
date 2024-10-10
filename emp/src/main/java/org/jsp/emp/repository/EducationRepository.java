package org.jsp.emp.repository;

import java.util.List;
import java.util.Optional;
import org.jsp.emp.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface EducationRepository extends JpaRepository<Education, Integer>
{

	Education save(Education education);

	Optional<Education> findById(int id);

	@Query("select e from Education e where e.employee.id=:eid")
	List<Education> findAllEducationsByEid(int eid);


	@Query("select e from Education e where e.employee.id=:eid and e.id=:edid")
	Optional<Education> findEducationById(int eid, int edid);
	
	@Modifying
	@Transactional
	@Query("delete from Education e where e.employee.id=:eid and e.id=:edid")
	void deleteEducationById(int eid, int edid);
}
