package com.GraphiFlow.project_PSC.services;

import com.GraphiFlow.project_PSC.entities.Project;
import com.GraphiFlow.project_PSC.entities.Task;
import com.GraphiFlow.project_PSC.entities.enums.ProjectStatus;
import com.GraphiFlow.project_PSC.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

// Classe de serviço para operações relacionadas a Project
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskService taskService;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project createProjectWithTask(Task task) {
        Project project = new Project();
        project.setMoment(Instant.now());
        project.setProjectStatus(ProjectStatus.WAITING_DELIVERY);
        return projectRepository.save(project);
    }
}
