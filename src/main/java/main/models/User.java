package main.models;

import lombok.Getter;
import lombok.Setter;

public class User {
    
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    public User(String name, String email, String password){

        this.name = name;

        this.email = email;

        this.password = password;
    }

}
