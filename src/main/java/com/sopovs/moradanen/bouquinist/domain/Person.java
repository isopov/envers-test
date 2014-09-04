package com.sopovs.moradanen.bouquinist.domain;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.springframework.data.domain.Sort;

import com.google.common.collect.ComparisonChain;

@Entity
@Audited
@Table(name = "PERSON")
public class Person extends AbstractEntity {

	public static Sort DEFAULT_SORT = new Sort("lastName", "firstName", "secondName", "id");

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

	public static final Comparator<Person> BY_FIRSTNAME = new Comparator<Person>() {
		@Override
		public int compare(Person o1, Person o2) {
			return ComparisonChain.start()
					.compare(o1.firstName, o2.firstName)
					.compare(o1.getId(), o2.getId())
					.result();
		}
	};

}
