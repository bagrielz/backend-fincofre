package br.com.fincofre.api.domain.spent;

import org.springframework.data.jpa.repository.JpaRepository;

// Repositório JPA para a entidade Spent, fornecendo métodos CRUD automaticamente
public interface SpentRepository extends JpaRepository<Spent, Long> {
}
