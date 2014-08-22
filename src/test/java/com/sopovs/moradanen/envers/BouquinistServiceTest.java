package com.sopovs.moradanen.envers;

import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Test;

import com.sopovs.moradanen.bouquinist.domain.Person;

public class BouquinistServiceTest extends AbstractTest {

	@Test
	public void testGetRandomPerson() {
		Person person = bouquinistService.getRandomPerson(new Random());
		assertNotNull(person.getId());
		assertNotNull(person.getFirstName());
		assertNotNull(person.getLastName());
	}
}
