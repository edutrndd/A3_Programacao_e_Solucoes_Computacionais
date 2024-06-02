package com.GraphiFlow.project_PSC.repositories;

import com.GraphiFlow.project_PSC.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Será um repositório para a entidade User.
@Repository // Indica que essa classe é um repositório, facilitando a injeção de dependência pelo Spring
public interface UserRepository extends JpaRepository<User, Long> {
    // Interface que estende JpaRepository, indicando que é um repositório de User, que tem identificador do tipo Long
}
