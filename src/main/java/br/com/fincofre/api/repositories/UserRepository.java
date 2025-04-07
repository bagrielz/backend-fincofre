package br.com.fincofre.api.repositories;

import br.com.fincofre.api.models.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String username);

    void deleteByLogin(String username);

    User getReferenceByLogin(String username);

}
