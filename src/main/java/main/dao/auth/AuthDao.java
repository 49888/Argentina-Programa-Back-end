package main.dao.auth;

import main.models.UserDatabase;

public interface AuthDao {
    
    UserDatabase findUserByEmail(String email);
}
