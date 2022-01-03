package br.com.authenticator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.authenticator.exception.ExistsObjectException;
import br.com.authenticator.exception.LengthPasswordException;
import br.com.authenticator.middleware.HashPassword;
import br.com.authenticator.models.Person;
import br.com.authenticator.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person save(Person person) {
		boolean emailExist = personRepository.findByEmail(person.getEmail())
				.stream()
				.anyMatch(email -> !email.equals(person.getEmail()));
		
		boolean userNameExist = personRepository.findByUsername(person.getUsername())
				.stream()
				.anyMatch(username -> !username.equals(person.getUsername()));
				
		if(emailExist) {
			throw new ExistsObjectException("E-mail já em uso.");
		} 
		
		if(userNameExist) {
			throw new ExistsObjectException("Nome de usuário já em uso.");
		}
		
		if(person.getPassword().length() < 3 || person.getPassword().length() > 8) {
			throw new LengthPasswordException("Senha deve conter no mínimo 3 caracteres e no máximo 8 caracteres.");
		}
		
		HashPassword hash = new HashPassword();
		person.setPassword(hash.hashGenerator(person.getPassword()));
		
		return personRepository.save(person);
	}
}
