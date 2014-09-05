package com.sopovs.moradanen.bouquinist.services;

import static com.sopovs.moradanen.bouquinist.domain.QBook.book;
import static com.sopovs.moradanen.bouquinist.domain.QEdition.edition;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mysema.query.jpa.impl.JPAQuery;
import com.sopovs.moradanen.bouquinist.domain.Person;
import com.sopovs.moradanen.bouquinist.domain.QPerson;
import com.sopovs.moradanen.bouquinist.dto.PersonDetailsDTO;
import com.sopovs.moradanen.bouquinist.repositories.EditionRepository;
import com.sopovs.moradanen.bouquinist.repositories.PersonRepository;

@Service
public class BouquinistServiceImpl implements BouquinistService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private EditionRepository editionRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public Person getRandomPerson(Random random) {
		long count = personRepository.count();
		PageRequest pageRequest = new PageRequest(random.nextInt((int) count), 1, new Sort("firstName", "lastName",
				"secondName", "id"));
		Page<Person> findAll = personRepository.findAll(pageRequest);
		Preconditions.checkState(findAll.getNumberOfElements() == 1);
		return findAll.getContent().get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public PersonDetailsDTO getPersonDetails(Long personId) {

		Person person = personRepository.getOne(personId);
		long editionCount = editionRepository.countByEditorId(personId);

		long authorCount = new JPAQuery(em).from(book, edition, QPerson.person)
				.where(book.editions.contains(edition)
						, edition.authors.contains(QPerson.person)
						, QPerson.person.id.eq(personId))
				.count();

		return new PersonDetailsDTO(person, authorCount, editionCount);

	}
}
