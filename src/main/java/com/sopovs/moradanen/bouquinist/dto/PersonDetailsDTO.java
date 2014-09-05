package com.sopovs.moradanen.bouquinist.dto;

import com.sopovs.moradanen.bouquinist.domain.Person;

public class PersonDetailsDTO {
	private final String firstName;
	private final String secondName;
	private final String lastName;

	private final long numberOfBooksAsAuthor;
	private final long numberOfBooksAsEditor;

	public PersonDetailsDTO(Person person, long numberOfBooksAsAuthor, long numberOfBooksAsEditor) {
		firstName = person.getFirstName();
		secondName = person.getSecondName();
		lastName = person.getLastName();
		this.numberOfBooksAsAuthor = numberOfBooksAsAuthor;
		this.numberOfBooksAsEditor = numberOfBooksAsEditor;
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

	public long getNumberOfBooksAsAuthor() {
		return numberOfBooksAsAuthor;
	}

	public long getNumberOfBooksAsEditor() {
		return numberOfBooksAsEditor;
	}
}
