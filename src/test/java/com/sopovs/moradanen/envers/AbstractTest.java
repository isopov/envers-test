package com.sopovs.moradanen.envers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionTemplate;

import com.sopovs.moradanen.bouquinist.BouquinistApplication;
import com.sopovs.moradanen.bouquinist.repositories.BookRepository;
import com.sopovs.moradanen.bouquinist.repositories.CirculationRepository;
import com.sopovs.moradanen.bouquinist.repositories.EditionRepository;
import com.sopovs.moradanen.bouquinist.repositories.PersonRepository;
import com.sopovs.moradanen.bouquinist.services.BouquinistService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BouquinistApplication.class)
public abstract class AbstractTest {

	@Autowired
	protected PersonRepository personRepository;
	@Autowired
	protected BookRepository bookRepository;
	@Autowired
	protected EditionRepository editionRepository;
	@Autowired
	protected CirculationRepository circulationRepository;
	@PersistenceContext
	protected EntityManager em;
	@Autowired
	protected TransactionTemplate transactionTemplate;
	@Autowired
	protected BouquinistService bouquinistService;

}
