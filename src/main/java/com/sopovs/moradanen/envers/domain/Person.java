package com.sopovs.moradanen.envers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "PERSON")
public class Person extends AbstractEntity {

	private String firstName;
	private String secondName;
	private String lastName;

	public Person() {
	}

	public Person(String firstName, String secondName, String lastName) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

}
