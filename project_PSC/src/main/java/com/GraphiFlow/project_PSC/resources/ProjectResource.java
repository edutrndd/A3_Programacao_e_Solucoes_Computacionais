package com.GraphiFlow.project_PSC.resources;

import com.GraphiFlow.project_PSC.entities.Project;
import com.GraphiFlow.project_PSC.entities.Task;
import com.GraphiFlow.project_PSC.services.ProjectService;
import com.GraphiFlow.project_PSC.services.TaskService;
import com.GraphiFlow.project_PSC.entities.enums.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

// Controlador REST para resources relacionados a projetos.
@RestController
@RequestMapping(value = "/projects")
public class ProjectResource {

    // Injeção de dependência do serviço de ProjectService
    @Autowired
    private ProjectService projectService;

    // Injeção de dependência do serviço de tarefa
    @Autowired
    private TaskService taskService;

    // Endpoint para buscar todos os projetos
    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        // Chama o serviço para encontrar todos os projetos
        List<Project> list = projectService.findAll();
        // Retorna uma resposta HTTP com a lista de projetos
        return ResponseEntity.ok().body(list);
    }

    // Endpoint para buscar um projeto por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id) {
        // Chama o serviço para encontrar o projeto com o ID fornecido
        Project obj = projectService.findById(id);
        // Retorna uma resposta HTTP com o projeto encontrado
        return ResponseEntity.ok().body(obj);
    }

    // Endpoint para criar um projeto com uma tarefa
    @PostMapping
    public ResponseEntity<Project> createProjectWithTask(
            // Parâmetros da requisição para criar a tarefa
            @RequestParam String taskName,
            @RequestParam String taskDescription,
            @RequestParam(required = false) String taskUrlImg) {

        // Cria um novo projeto
        Project project = new Project();
        // Define o momento atual como momento de criação do projeto
        project.setMoment(Instant.now());
        // Define o status do projeto como "WAITING_DELIVERY"
        project.setProjectStatus(ProjectStatus.WAITING_DELIVERY);
        // Salva o projeto usando o serviço correspondente
        project = projectService.save(project);

        // Cria uma nova tarefa
        Task task = new Task();
        // Define o nome, descrição e URL da imagem da tarefa com os parâmetros fornecidos
        task.setName(taskName);
        task.setDescription(taskDescription);
        task.setImgUrl(taskUrlImg);
        // Salva a tarefa usando o serviço correspondente
        taskService.save(task);

        // Retorna uma resposta HTTP com o projeto criado
        return ResponseEntity.ok().body(project);
    }
}