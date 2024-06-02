package com.GraphiFlow.project_PSC.repositories;

import com.GraphiFlow.project_PSC.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Será um repositório para a entidade Category.
@Repository // Indica que essa classe é um repositório, facilitando a injeção de dependência pelo Spring
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Interface que estende JpaRepository, indicando que é um repositório de Category, que tem identificador do tipo Long
}
