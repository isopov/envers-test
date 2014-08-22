package com.sopovs.moradanen.bouquinist.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.sopovs.moradanen.bouquinist.domain.Person;
import com.sopovs.moradanen.bouquinist.repositories.PersonRepository;

@Service
public class BouquinistService {

	@Autowired
	private PersonRepository personRepository;

	public Person getRandomPerson(Random random) {
		long count = personRepository.count();
		PageRequest pageRequest = new PageRequest(random.nextInt((int) count), 1, new Sort("firstName", "lastName",
				"secondName", "id"));
		Page<Person> findAll = personRepository.findAll(pageRequest);
		Preconditions.checkState(findAll.getNumberOfElements() == 1);
		return findAll.getContent().get(0);
	}
}
