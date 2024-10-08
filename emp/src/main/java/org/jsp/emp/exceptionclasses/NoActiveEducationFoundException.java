package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoActiveEducationFoundException extends RuntimeException
{
    private String message;
	
	public NoActiveEducationFoundException(String message) 
	{
		this.message = message;
	}
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
}
