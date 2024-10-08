package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCredentialssException extends RuntimeException
{
private String message;
	
	public InvalidCredentialssException(String message) 
	{
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return this.message;
	}
}
