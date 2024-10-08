package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCredentialException extends RuntimeException
{
private String message;
	
	public InvalidCredentialException(String message) 
	{
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return this.message;
	}
}
