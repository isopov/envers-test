package com.sopovs.moradanen.envers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sopovs.moradanen.envers.domain.Person;
import com.sopovs.moradanen.envers.repositories.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EversTestApplication.class)
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testSimple() {
		Person person = new Person();
		personRepository.save(person);
		assertNotNull(person.getId());
	}

}
