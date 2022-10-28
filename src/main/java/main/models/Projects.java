package main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "projects") 
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Projects {
    
    @Id @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "github")
    private String github;

    @Column(name = "ghpages")
    private String ghPages;


    @Override
    public Projects clone() {
        
        return new Projects(id, name, description, github, ghPages);
    }
}
