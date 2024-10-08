package org.jsp.emp.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class InvalidAddressIdException extends RuntimeException
{
    private String message;
	
	public InvalidAddressIdException(String message) 
	{
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return this.message;
	}
}
