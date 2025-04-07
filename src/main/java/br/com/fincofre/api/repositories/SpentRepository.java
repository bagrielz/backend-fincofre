package br.com.fincofre.api.repositories;

import br.com.fincofre.api.models.entities.spent.Spent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repositório JPA que fornece métodos CRUD para a entidade Spent
public interface SpentRepository extends JpaRepository<Spent, Long> {

    List<Spent> findByUserId(Long user);

}
