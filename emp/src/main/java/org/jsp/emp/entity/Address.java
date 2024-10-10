package org.jsp.emp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int doorNo;
	@Column(unique = true)
	private String streetAddress;
	@Column(unique = true)
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String addressType;
	
	@ManyToOne
	private Employee employee;


}
