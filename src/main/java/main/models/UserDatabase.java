package main.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity @Table(name = "users")
@Data @AllArgsConstructor @NoArgsConstructor
public class UserDatabase {


    @Id @Column(name = "email", unique = true)
    private String email;
    
    @Column(name = "name")
    private String name;

    
    @Column(name = "password")
    private String password;
    
    @Column(name = "roles")
    private String roles;
}
//*/