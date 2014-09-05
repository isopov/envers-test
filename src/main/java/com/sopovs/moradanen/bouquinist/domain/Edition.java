package com.sopovs.moradanen.bouquinist.domain;

import static java.util.Arrays.asList;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.joda.time.LocalDate;

@Entity
@Audited
@Table(name = "EDITION")
public class Edition extends AbstractEntity {

	@ManyToOne
	private Book book;

	// There can be title variations between different editions
	private String title;
	// There can also be variations
	@ManyToMany
	private Collection<Person> authors;

	@ManyToOne
	private Person editor;

	public Edition() {
	}

	public Edition(Book book, Person editor, Collection<Person> authors) {
		this.book = book;
		this.editor = editor;
		this.authors = authors;
	}

	public Edition(Book book, Person editor, Person author) {
		this.book = book;
		this.editor = editor;
		this.authors = asList(author);
	}

	public Edition(Book book, String title, Collection<Person> authors, Person editor, LocalDate publishDate) {
		this.book = book;
		this.title = title;
		this.authors = authors;
		this.editor = editor;
		this.publishDate = publishDate;
	}

	private LocalDate publishDate;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getEditor() {
		return editor;
	}

	public void setEditor(Person editor) {
		this.editor = editor;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public Collection<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(Collection<Person> authors) {
		this.authors = authors;
	}

}
