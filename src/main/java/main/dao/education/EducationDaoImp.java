package main.dao.education;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import main.models.Education;

@Repository @Transactional
public class EducationDaoImp implements EducationDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Education> getEducation() {

        String query = "FROM Education";

        List<Education> result = entityManager.createQuery(query).getResultList();
        
        return result;
    }

    @Override
    public void createEducation(Education education) {
        
        entityManager.merge(education);
    }

    @Override
    public void deleteEducation(long id) {
        
        Education education = entityManager.find(Education.class, id);

        entityManager.remove(education);
    }

    @Override
    public Education updateEducation(Long id, Education aux) {
        
        Education education = entityManager.find(Education.class, id);

        if(education != null) {

            if(aux.getTitle() != null) education.setTitle(aux.getTitle());

            if(aux.getDescription() != null) education.setDescription(aux.getDescription());

            if(aux.getImg() != null) education.setImg(aux.getImg());

            return education.clone();
        }
        else {

            return null;
        }
    }
    
}
