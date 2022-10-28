package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.dao.projects.ProjectsDao;
import main.models.Projects;

@RestController @RequestMapping("/api/projects")
public class ProjectsController {
    

    @Autowired
    private ProjectsDao projectsDao;

    //-> GET
    @RequestMapping(value = "/get")
    public List<Projects> getProjects(){

        List<Projects> list = projectsDao.getProjects();
        
        return list;
    }

    //-> DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProject(@PathVariable long id){

        Projects project = null;

        try {
            project = projectsDao.deleteProjects(id);
            
        } catch (Exception e) {

            return new ResponseEntity<String>("not found id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Projects>(project, HttpStatus.OK);
    }

    //-> CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Projects createProject(@RequestBody Projects project){

        return projectsDao.createProjects(project);
    }

    //-> UPDATE
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Projects updatProject(@PathVariable Long id, @RequestBody Projects projects){

        return projectsDao.updateProjects(id, projects);
    }
}
