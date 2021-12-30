package br.com.authenticator.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ErrorValidation {
	private Integer status;
	private LocalDateTime dateHour;
	private String title;
	private List<Field> fields;
	
	@Getter
	@AllArgsConstructor
	public static class Field{
		private String nameField;
		private String message;
	}
	
}
