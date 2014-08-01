package com.sopovs.moradanen.envers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sopovs.moradanen.envers.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
