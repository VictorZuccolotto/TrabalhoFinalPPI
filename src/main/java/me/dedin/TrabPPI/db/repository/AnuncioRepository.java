package me.dedin.TrabPPI.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import me.dedin.TrabPPI.db.model.Anuncio;

public interface AnuncioRepository extends CrudRepository<Anuncio, Long>{

	List<Anuncio> findFirst9ByOrderByDataDesc();
	
	List<Anuncio> findByCategoriaName(String name, Pageable sort);
	
	List<Anuncio> findByUserId(Long id, Pageable sort);
	
	Optional<List<Anuncio>> findByNomeContaining(String name, Pageable sort);
}
