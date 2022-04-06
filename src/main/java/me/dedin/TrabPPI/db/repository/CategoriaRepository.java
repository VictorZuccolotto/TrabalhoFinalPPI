package me.dedin.TrabPPI.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dedin.TrabPPI.db.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findByName(String name);
}
