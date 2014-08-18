package com.sopovs.moradanen.envers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.sopovs.moradanen.envers.domain.Person;
import com.sopovs.moradanen.envers.repositories.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EnversTestApplication.class)
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Test
	public void testSimple() {

		final Person person = new Person("Jon", "Smith");
		personRepository.save(person);
		person.setFirstName("John");
		personRepository.save(person);

		assertNotNull(person.getId());

		List<Number> revisions = transactionTemplate.execute(new TransactionCallback<List<Number>>() {

			@Override
			public List<Number> doInTransaction(TransactionStatus status) {

				AuditReader auditReader = AuditReaderFactory.get(em);
				return auditReader.getRevisions(Person.class, person.getId());
			}
		});

		assertEquals(2, revisions.size());

	}

}
