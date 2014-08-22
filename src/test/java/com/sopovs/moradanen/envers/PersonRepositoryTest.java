package com.sopovs.moradanen.envers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.sopovs.moradanen.bouquinist.domain.Person;

public class PersonRepositoryTest extends AbstractTest {

	@Test
	public void testAudit() {

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
