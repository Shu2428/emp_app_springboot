package org.jsp.emp.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;
@Builder

@NoArgsConstructor
public class NoEmployeeFoundException extends RuntimeException
{
	private String message;
	public NoEmployeeFoundException(String message)
	{
		this.message = message;
	}
	
	@Override
	public String getMessage() 
	{
		return this.message;
	}
}
