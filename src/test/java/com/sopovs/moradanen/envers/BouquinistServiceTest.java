package com.sopovs.moradanen.envers;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.Random;

import org.junit.Test;

import com.sopovs.moradanen.bouquinist.domain.Book;
import com.sopovs.moradanen.bouquinist.domain.Edition;
import com.sopovs.moradanen.bouquinist.domain.Person;
import com.sopovs.moradanen.bouquinist.dto.PersonDetailsDTO;

public class BouquinistServiceTest extends AbstractTest {

	@Test
	public void testGetRandomPerson() {
		personRepository.save(new Person("John", "Amber", "Smith"));
		Person person = bouquinistService.getRandomPerson(new Random());
		assertNotNull(person.getId());
		assertNotNull(person.getFirstName());
		assertNotNull(person.getLastName());
	}

	@Test
	public void testGetPersonDetailsWithoutBooks() {
		Person person = new Person("John", "Amber", "Smith");
		personRepository.save(person);
		assertNotNull(person.getId());
		PersonDetailsDTO personDetails = bouquinistService.getPersonDetails(person.getId());
		assertNotNull(personDetails);
		assertEquals(0L, personDetails.getNumberOfBooksAsAuthor());
		assertEquals(0L, personDetails.getNumberOfBooksAsEditor());
	}

	@Test
	public void testGetPersonDetailsAsSingleEditor() {
		Person person = new Person("John", "Amber", "Smith");
		personRepository.save(person);
		assertNotNull(person.getId());

		Book book = new Book();
		bookRepository.save(book);
		Edition edition = new Edition(book, person, Collections.<Person> emptyList());
		book.setEditions(asList(edition));
		editionRepository.save(edition);
		bookRepository.save(book);

		PersonDetailsDTO personDetails = bouquinistService.getPersonDetails(person.getId());
		assertNotNull(personDetails);
		assertEquals(0L, personDetails.getNumberOfBooksAsAuthor());
		assertEquals(1L, personDetails.getNumberOfBooksAsEditor());
	}

	@Test
	public void testGetPersonDetailsAsSingleAuthor() {
		Person person = new Person("John", "Amber", "Smith");
		personRepository.save(person);
		assertNotNull(person.getId());

		Book book = new Book();
		Edition edition = new Edition(book, null, person);
		bookRepository.save(book);
		book.setEditions(asList(edition));
		editionRepository.save(edition);
		bookRepository.save(book);

		PersonDetailsDTO personDetails = bouquinistService.getPersonDetails(person.getId());
		assertNotNull(personDetails);
		assertEquals(1L, personDetails.getNumberOfBooksAsAuthor());
		assertEquals(0L, personDetails.getNumberOfBooksAsEditor());
	}

}
