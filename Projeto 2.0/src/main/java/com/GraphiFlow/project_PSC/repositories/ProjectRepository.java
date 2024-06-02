package com.GraphiFlow.project_PSC.repositories;

import com.GraphiFlow.project_PSC.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Será um repositório para a entidade Project.
@Repository // Indica que essa classe é um repositório, facilitando a injeção de dependência pelo Spring
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Interface que estende JpaRepository, indicando que é um repositório de Project, que tem identificador do tipo Long

}
