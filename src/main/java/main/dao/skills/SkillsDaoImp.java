package main.dao.skills;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.models.Skill;


@Repository @Transactional
public class SkillsDaoImp implements SkillsDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Skill> getSkills() {

        String query = "FROM Skill";

        List<Skill> result = entityManager.createQuery(query).getResultList();
        
        return result;
    }
    
    @Override
    public void deleteSkill(Long id) {
        
        Skill skill = entityManager.find(Skill.class, id);

        entityManager.remove(skill);
    }

    @Override
    public void createSkill(Skill skill) {
        
        entityManager.merge(skill);  
    }

    @Override
    public Skill updateSkill(Long id, Skill aux) {
        
        Skill skill = entityManager.find(Skill.class, id);

        if(skill != null) {

            if(aux.getTitle() != null) skill.setTitle(aux.getTitle());

            if(aux.getPercentage() != -1) skill.setPercentage(aux.getPercentage());

            if(aux.getImageURL() != null) skill.setImageURL(aux.getImageURL());

            return skill.clone();
        }
        else {

            return null;
        }

        
    }
}
