package com.sopovs.moradanen.bouquinist.services;

import java.util.Random;

import com.sopovs.moradanen.bouquinist.domain.Person;
import com.sopovs.moradanen.bouquinist.dto.PersonDetailsDTO;

public interface BouquinistService {

	Person getRandomPerson(Random random);

	PersonDetailsDTO getPersonDetails(Long personId);
}
