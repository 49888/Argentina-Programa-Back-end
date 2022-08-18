package main.dao.experience;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import main.models.Experience;

@Repository @Transactional
public class ExperienceDaoImp implements ExperienceDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Experience> getExperience() {

        String query = "FROM Experience";

        List<Experience> result = entityManager.createQuery(query).getResultList();
        
        return result;
    }

    @Override
    public void createExperience(Experience experience) {
        
        entityManager.merge(experience);
    }

    @Override
    public void deleteExperience(long id) {
        
        Experience experience = entityManager.find(Experience.class, id);

        entityManager.remove(experience);
    }

    @Override
    public Experience updateExperience(Long id, Experience aux) {
        
        Experience experience = entityManager.find(Experience.class, id);

        if(experience != null){

            if(aux.getTitle() != null) experience.setTitle(aux.getTitle());

            if(aux.getDescription() != null) experience.setDescription(aux.getDescription());

            if(aux.getImg() != null) experience.setImg(aux.getImg());

            return experience.clone();
        }
        else {
            return null;
        }
    }
    
}
