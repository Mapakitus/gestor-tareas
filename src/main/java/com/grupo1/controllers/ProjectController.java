package com.grupo1.controllers;

import com.grupo1.entities.Project;
import com.grupo1.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    // Listar todos los proyectos
    @GetMapping
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "project/project-list";
    }

    // Mostrar formulario de creación
    @GetMapping("/new")
    public String showProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "project/project-form";
    }

    // Procesar creación de proyecto
    @PostMapping
    public String saveProject(@ModelAttribute Project project) {
        projectRepository.save(project);
        return "redirect:/projects";
    }

    // Mostrar formulario de edición
    @GetMapping("/{id}")
    public String editProjectForm(@PathVariable Long id, Model model) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            return "redirect:/projects";
        }
        model.addAttribute("project", project.get());
        return "project/project-form";
    }

    // Procesar actualización
    @PostMapping("/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute Project project) {
        project.setId(id);
        projectRepository.save(project);
        return "redirect:/projects";
    }

    // Eliminar proyecto
    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "redirect:/projects";
    }
}