package com.sopovs.moradanen.bouquinist.dto;

import com.sopovs.moradanen.bouquinist.domain.Person;

public class PersonDetailsDTO {
	private final String firstName;
	private final String secondName;
	private final String lastName;

	public PersonDetailsDTO(Person person) {
		firstName = person.getFirstName();
		secondName = person.getSecondName();
		lastName = person.getLastName();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getLastName() {
		return lastName;
	}
}
