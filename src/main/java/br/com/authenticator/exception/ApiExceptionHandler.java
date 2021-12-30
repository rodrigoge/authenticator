package br.com.authenticator.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
		
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorValidation.Field> fields = new ArrayList<>();
		
		/* get all erros and setting fieldname and error message */
		for (ObjectError obj : ex.getBindingResult().getAllErrors()) {
			String nameField = ((FieldError) obj).getField();
			String messageField = obj.getDefaultMessage();
			
			fields.add(new ErrorValidation.Field(nameField, messageField));
		}
		
		/* class error */
		ErrorValidation error = new ErrorValidation();
		error.setStatus(status.value());
		error.setDateHour(LocalDateTime.now());
		error.setFields(fields);
		
		return handleExceptionInternal(ex, error , headers, status, request);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
				
		ErrorValidation error = new ErrorValidation();
		error.setStatus(status.value());
		error.setDateHour(LocalDateTime.now());
		error.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, error , new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ExistsObjectException.class)
	public ResponseEntity<Object> handleExistsObjectException(ExistsObjectException ex, WebRequest request){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
				
		ErrorValidation error = new ErrorValidation();
		error.setStatus(status.value());
		error.setDateHour(LocalDateTime.now());
		error.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, error , new HttpHeaders(), status, request);
	}
}
