package br.com.authenticator.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.authenticator.models.Person;
import br.com.authenticator.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RequestMapping("/")
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping
	public List<Person> findAllPeople() {
		return personService.findAll();
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Person save(@Valid @RequestBody Person person) {
		return personService.save(person);
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public Person login(@RequestBody Map<String, String> userCredenciais) {
		return personService.login(userCredenciais.get("emailOrUsername"), userCredenciais.get("password"));
	}

}
