package main.dao.auth;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import main.models.UserDatabase;

@Repository @Transactional
public class AuthDaoImp implements AuthDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDatabase findUserByEmail(String email) {
        
        UserDatabase user = entityManager.find(UserDatabase.class, email);


        return user;
    }
    
}
