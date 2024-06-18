package com.GraphiFlow.project_PSC.services;

import com.GraphiFlow.project_PSC.entities.Project;
import com.GraphiFlow.project_PSC.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de serviço para operações relacionadas a Project
@Service
public class ProjectService {

    // Injeção de dependência do repositório ProjectRepository
    @Autowired
    private ProjectRepository repository;

    // Método para buscar todos os projetos
    public List<Project> findAll() {
        // Chama o método findAll() do repositório para obter a lista de projetos
        return repository.findAll();
    }

    // Método para buscar um projeto por ID
    public Project findById(Long id) {
        // Chama o método findById() do repositório para obter o projeto com o ID fornecido
        // Retorna null caso o projeto não seja encontrado
        return repository.findById(id).orElse(null);
    }

    // Método para salvar um novo projeto ou atualizar um existente
    public Project save(Project project) {
        // Chama o método save() do repositório para salvar o projeto fornecido
        return repository.save(project);
    }
}