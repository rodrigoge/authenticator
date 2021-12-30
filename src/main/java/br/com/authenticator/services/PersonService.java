package br.com.authenticator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.authenticator.exception.ExistsObjectException;
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
	
	private PasswordEncoder encoder;
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	public Person save(Person person) {
		boolean emailExist = personRepository.findByEmail(person.getEmail())
				.stream()
				.anyMatch(email -> !email.equals(person.getEmail()));
		
		boolean userNameExist = personRepository.findByUsername(person.getUsername())
				.stream()
				.anyMatch(username -> !username.equals(person.getUsername()));
				
		if(emailExist) {
			throw new ExistsObjectException("E-mail já em uso.");
		} else if(userNameExist) {
			throw new ExistsObjectException("Nome de usuário já em uso.");
		}
		
		person.setPassword(encoder.encode(person.getPassword()));
		
		return personRepository.save(person);
	}
}
