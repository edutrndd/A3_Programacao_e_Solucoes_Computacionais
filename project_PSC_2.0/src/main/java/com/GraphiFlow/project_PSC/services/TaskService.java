package com.GraphiFlow.project_PSC.services;

import com.GraphiFlow.project_PSC.entities.Category;
import com.GraphiFlow.project_PSC.entities.Task;
import com.GraphiFlow.project_PSC.repositories.CategoryRepository;
import com.GraphiFlow.project_PSC.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Classe de serviço para operações relacionadas a Task
@Service
public class TaskService {

    // Injeção de dependência do repositório TaskRepository
    @Autowired
    private TaskRepository repository;

    // Injeção de dependência do repositório CategoryRepository
    @Autowired
    private CategoryRepository categoryRepository;

    // Método para buscar todas as tarefas agrupadas por categoria
    public Map<String, List<Task>> findAllGroupedByCategory() {
        // Obtém todas as tarefas do repositório
        List<Task> tasks = repository.findAll();

        // Agrupa as tarefas por categoria e retorna o resultado
        return tasks.stream()
                .filter(task -> !task.getCategories().isEmpty()) // Filtra tarefas sem categorias
                .collect(Collectors.groupingBy(task -> task.getCategories().iterator().next().getName()));
    }

    // Método para buscar uma tarefa por ID
    public Task findById(Long id) {
        // Busca a tarefa com o ID fornecido no repositório
        // Se não encontrada, retorna null
        return repository.findById(id).orElse(null);
    }

    // Método para salvar uma tarefa no banco de dados
    public Task save(Task task) {
        // Salva a tarefa no repositório e retorna a instância salva
        return repository.save(task);
    }

    // Método para criar uma nova tarefa associada a uma categoria específica
    public Task createTask(String taskName, String taskDescription, String taskUrlImg, Long categoryId) {
        // Cria uma nova instância de Task com os dados fornecidos
        Task task = new Task(null, taskName, taskDescription, taskUrlImg);
        // Obtém a categoria correspondente ao ID fornecido
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        // Adiciona a categoria à lista de categorias da tarefa
        task.getCategories().add(category);
        // Salva a nova tarefa no repositório e retorna a instância salva
        return repository.save(task);
    }
}
