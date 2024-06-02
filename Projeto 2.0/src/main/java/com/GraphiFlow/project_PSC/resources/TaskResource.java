package com.GraphiFlow.project_PSC.resources;

import com.GraphiFlow.project_PSC.entities.Task;
import com.GraphiFlow.project_PSC.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Controlador REST para resources relacionados a tarefas.
@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

    // Injeção de dependência do serviço de TaskService
    @Autowired
    private TaskService service;

    // Endpoint para buscar todas as tarefas agrupadas por categoria
    @GetMapping("/groupedByCategory")
    public ResponseEntity<Map<String, List<Task>>> findAllGroupedByCategory() {
        // Chama o serviço para encontrar tarefas agrupadas por categoria
        Map<String, List<Task>> tasksByCategory = service.findAllGroupedByCategory();
        // Retorna uma resposta HTTP 200 (OK) com o corpo contendo as tarefas agrupadas
        return ResponseEntity.ok().body(tasksByCategory);
    }

    // Endpoint para buscar uma tarefa por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        // Chama o serviço para encontrar a tarefa pelo ID fornecido
        Task obj = service.findById(id);
        // Retorna uma resposta HTTP 200 (OK) com o corpo contendo a tarefa encontrada
        return ResponseEntity.ok().body(obj);
    }

    // Endpoint para criar uma nova tarefa
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> taskData) {
        // Extrai os dados da nova tarefa do corpo da requisição
        String taskName = taskData.get("taskName");
        String taskDescription = taskData.get("taskDescription");
        String taskUrlImg = taskData.get("taskUrlImg");
        Long categoryId = Long.parseLong(taskData.get("categoryId"));

        // Chama o serviço para criar uma nova tarefa com os dados fornecidos
        Task newTask = service.createTask(taskName, taskDescription, taskUrlImg, categoryId);
        // Retorna uma resposta HTTP 200 (OK) com o corpo contendo a nova tarefa criada
        return ResponseEntity.ok().body(newTask);
    }
}
