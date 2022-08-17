package main.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
