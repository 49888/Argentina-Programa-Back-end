package main.dao.education;

import java.util.Iterator;
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
    public Education createEducation(Education education) {
        
        Education aux = entityManager.merge(education).clone();


        List<Education> result = entityManager.createQuery("FROM Education").getResultList();

        Long id = 0l;
        
        Iterator<Education> iterable = result.iterator();

        while(iterable.hasNext()){

            Education item = iterable.next();

            if(item.getId() > id) id = item.getId();
        }

        aux.setId(id);

        return aux;
    }

    @Override
    public Education deleteEducation(long id) {
        
        Education education = entityManager.find(Education.class, id);

        Education aux = education.clone();

        entityManager.remove(education);

        return aux;
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
