package org.jsp.emp.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder

@NoArgsConstructor
public class NoAddressFoundException extends RuntimeException
{
	private String message;
	public NoAddressFoundException(String message)
	{
		this.message = message;
	}
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
}
