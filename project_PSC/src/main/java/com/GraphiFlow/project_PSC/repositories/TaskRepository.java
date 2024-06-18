package com.GraphiFlow.project_PSC.repositories;

import com.GraphiFlow.project_PSC.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Será um repositório para a entidade Task.
@Repository // Indica que essa classe é um repositório, facilitando a injeção de dependência pelo Spring
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Interface que estende JpaRepository, indicando que é um repositório de Task, que tem identificador do tipo Long
}
