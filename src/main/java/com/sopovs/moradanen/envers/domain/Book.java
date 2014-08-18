package com.sopovs.moradanen.envers.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "BOOK")
public class Book extends AbstractEntity {

	@OneToMany
	private List<Edition> editions;

	public List<Edition> getEditions() {
		return editions;
	}

	public void setEditions(List<Edition> editions) {
		this.editions = editions;
	}

}
