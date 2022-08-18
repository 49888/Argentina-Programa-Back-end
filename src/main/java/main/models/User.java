package main.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "users")
public class User {

    @Id @Getter @Setter @Column(name = "id")
    private long id;
    
    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "mail")
    private String email;

    @Getter @Setter @Column(name = "password")
    private String password;


    public User(){}

    public User(String name, String email, String password){

        this.name = name;

        this.email = email;

        this.password = password;
    }

}
//*/