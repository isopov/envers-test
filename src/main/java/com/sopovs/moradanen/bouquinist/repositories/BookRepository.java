package com.sopovs.moradanen.bouquinist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sopovs.moradanen.bouquinist.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
