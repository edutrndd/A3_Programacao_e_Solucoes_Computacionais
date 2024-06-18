package com.GraphiFlow.project_PSC.services;

import com.GraphiFlow.project_PSC.entities.Category;
import com.GraphiFlow.project_PSC.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Classe de serviço para operações relacionadas a Category
@Service
public class CategoryService {

    // Injeção de dependência do repositório CategoryRepository
    @Autowired
    private CategoryRepository repository;

    // Método para buscar todas as categorias
    public List<Category> findAll() {
        // Chama o método findAll() do repositório para obter a lista de categorias
        return repository.findAll();
    }

    // Método para buscar uma categoria por ID
    public Category findById(Long id) {
        // Chama o método findById() do repositório para obter a categoria com o ID fornecido
        // Caso a categoria não seja encontrada, lança uma exceção RuntimeException
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }
}