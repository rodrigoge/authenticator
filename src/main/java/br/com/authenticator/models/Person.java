package br.com.authenticator.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Getter @Setter @Column
	@NotBlank(message = "Nome é obrigatório.")
	@Size(min = 3, max = 255, message = "Nome deve conter no mínimo {min} caracteres e no máximo {max} de caracteres.")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "Primeira letra deve ser maiúscula.")
	private String name;
	
	@Getter @Setter @Column
	@NotBlank(message = "Nome de usuário é obrigatório.")
	@Size(min = 3, max = 10, message = "Nome de usuário deve conter no mínimo {min} caracteres e no máximo {max} de caracteres.")
	private String username;
	
	@Getter @Setter @Column
	@Email(message = "E-mail inválido.")
	@NotBlank(message = "E-mail é obrigatório.")
	@Size(min = 3, max = 255, message = "E-mail deve conter no mínimo {min} caracteres e no máximo {max} de caracteres.")
	private String email;
	
	@Getter @Setter @Column
	@NotBlank(message = "Senha é obrigatória.")
	private String password;
	
	@Getter @Setter @Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data de nascimento é obrigatória.")
	@Past(message = "Data de nascimento inválida.")
	private LocalDate birthdate;
	
	@Getter @Setter @Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Gênero é obrigatório.")
	private GenderEnum gender;
	
	@Getter @Setter @Column
	@NotBlank(message = "Telefone é obrigatória.")
	@Size(min = 10, max = 11, message = "Telefone deve conter no mínimo {min} caracteres e no máximo {max} de caracteres.")
	private String phone;
}
