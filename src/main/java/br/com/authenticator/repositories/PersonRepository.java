package br.com.authenticator.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.authenticator.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	Optional<Person> findByEmail(String email);

	Optional<Person> findByUsername(String username);

	Optional<Person> findByPassword(String password);

	Person findPersonByEmail(String email);

	Person findPersonByUsername(String username);
}
