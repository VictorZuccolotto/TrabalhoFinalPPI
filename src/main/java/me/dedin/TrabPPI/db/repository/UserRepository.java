package me.dedin.TrabPPI.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dedin.TrabPPI.db.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	
//    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.nome= (:nome)")
//	@Param("nome")
    public User findByNome(String nome);
    
    public Optional<User> findByEmail(String email);
    
}
