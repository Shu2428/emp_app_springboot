package org.jsp.emp.entity;

import org.jsp.emp.util.EmployeeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(unique = true) //Annotated Column Will Be Always Unique
	private String email;
	@Column(unique = true)
	private long phone;
	private String password;
	@Enumerated(EnumType.STRING) //To Make 0 to 1 and 1 to 0
	private EmployeeStatus status;
	
	
}
