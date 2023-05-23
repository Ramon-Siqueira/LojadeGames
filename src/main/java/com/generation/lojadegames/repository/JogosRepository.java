package com.generation.lojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.lojadegames.model.jogos;

public interface JogosRepository extends JpaRepository<jogos, Long>{

	public List <jogos> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
