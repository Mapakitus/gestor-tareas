package com.grupo1.controllers;

import com.grupo1.entities.Project;
import com.grupo1.repositories.ProjectRepository;
import com.grupo1.repositories.TaskRepository;
import com.grupo1.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("project")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectController(ProjectRepository projectRepository,
            TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    //    @GetMapping("/detail")
    //    public String detailUser() {
    //        return "/project/project-detail";
    //    }

    @GetMapping
    public String getProject(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("project", projects);
        return "/project/project-list";
    }

    @GetMapping("/new")
    public String projectForm(Model model) {
        model.addAttribute("project", new Project());
        return "/project/project-form";
    }

    @GetMapping("/project/{id}")
    public String findById(Model model, @PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);

        if (project.isPresent()) {
            model.addAttribute("project", project.get());
        } else {
            model.addAttribute("error", "Proyecto no encontrado.");
        }

        return "project/project-detail";
    }
}
