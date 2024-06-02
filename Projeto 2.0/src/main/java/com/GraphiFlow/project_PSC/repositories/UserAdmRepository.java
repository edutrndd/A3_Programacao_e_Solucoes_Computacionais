package com.GraphiFlow.project_PSC.repositories;

import com.GraphiFlow.project_PSC.entities.UserAdm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Será um repositório para a entidade UserAdm.
@Repository // Indica que essa classe é um repositório, facilitando a injeção de dependência pelo Spring
public interface UserAdmRepository extends JpaRepository<UserAdm, Long> {
    // Interface que estende JpaRepository, indicando que é um repositório de UserAdm, que tem identificador do tipo Long
}

