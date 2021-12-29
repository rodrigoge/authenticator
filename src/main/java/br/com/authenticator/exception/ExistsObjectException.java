package br.com.authenticator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExistsObjectException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExistsObjectException(String message) {
		super(message);
	}
}
