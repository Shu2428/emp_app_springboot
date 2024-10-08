package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoActiveAddressFoundException extends RuntimeException
{
private String message;
	
	public NoActiveAddressFoundException(String message) 
	{
		this.message = message;
	}
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
}
