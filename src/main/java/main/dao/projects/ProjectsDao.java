package main.dao.projects;

import java.util.List;

import main.models.Projects;

public interface ProjectsDao {
    
    List<Projects> getProjects();

    Projects deleteProjects(long id);

    Projects createProjects(Projects experience);

    Projects updateProjects(Long id, Projects experience);
}
