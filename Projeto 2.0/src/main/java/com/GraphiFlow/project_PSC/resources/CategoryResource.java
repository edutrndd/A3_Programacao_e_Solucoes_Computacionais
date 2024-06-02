package com.GraphiFlow.project_PSC.resources;

import com.GraphiFlow.project_PSC.entities.Category;
import com.GraphiFlow.project_PSC.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controlador REST para resources relacionados a categorias.
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    // Injeção de dependência do serviço CategoryService
    @Autowired
    private CategoryService service;

    // Endpoint para buscar todas as categorias
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        // Chama o método findAll() do CategoryService para obter a lista de categorias
        List<Category> list = service.findAll();
        // Retorna a lista de categorias dentro de uma resposta HTTP com status OK (200)
        return ResponseEntity.ok().body(list);
    }

    // Endpoint para buscar uma categoria por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        // Chama o método findById(Long id) do CategoryService para buscar a categoria correspondente ao ID fornecido
        Category obj = service.findById(id);
        // Retorna a categoria dentro de uma resposta HTTP com status OK (200)
        return ResponseEntity.ok().body(obj);
    }
}
