package br.com.fincofre.api.domain.spent;

import org.springframework.data.jpa.repository.JpaRepository;

// Repositório JPA que fornece métodos CRUD para a entidade Spent
public interface SpentRepository extends JpaRepository<Spent, Long> {
}
