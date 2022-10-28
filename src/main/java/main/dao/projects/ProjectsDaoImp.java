package main.dao.projects;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import main.models.Projects;

@Repository @Transactional
public class ProjectsDaoImp implements ProjectsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Projects> getProjects() {

        String query = "FROM Projects";

        List<Projects> result = entityManager.createQuery(query).getResultList();
        
        return result;
    }

    @Override
    public Projects deleteProjects(long id) {

        Projects project = entityManager.find(Projects.class, id);

        Projects aux = project.clone();

        entityManager.remove(project);

        return aux;
    }

    @Override
    public Projects createProjects(Projects project) {

        Projects aux = entityManager.merge(project).clone();


        List<Projects> result = entityManager.createQuery("FROM Projects").getResultList();

        Long id = 0l;
        
        Iterator<Projects> iterable = result.iterator();

        while(iterable.hasNext()){

            Projects item = iterable.next();

            if(item.getId() > id) id = item.getId();
        }

        aux.setId(id);

        return aux;
    }

    @Override
    public Projects updateProjects(Long id, Projects aux) {
        
        Projects project = entityManager.find(Projects.class, id);

        if(project != null){

            if(aux.getName() != null) project.setName(aux.getName());

            if(aux.getDescription() != null) project.setDescription(aux.getDescription());

            if(aux.getGhPages() != null) project.setGhPages(aux.getGhPages());

            if(aux.getGithub() != null) project.setGithub(aux.getGithub());

            return project.clone();
        }
        else {
            return null;
        }
    }

    
}