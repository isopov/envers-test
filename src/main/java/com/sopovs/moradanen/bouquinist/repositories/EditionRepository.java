package com.sopovs.moradanen.bouquinist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sopovs.moradanen.bouquinist.domain.Edition;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {

	long countByEditorId(Long editorId);

}
