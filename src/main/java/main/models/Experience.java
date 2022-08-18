package main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity @Table(name = "experience") 
public class Experience {
    
    @Id @Getter @Setter @Column(name = "id")
    private long id;

    @Getter @Setter @Column(name = "title")
    private String title;

    @Getter @Setter @Column(name = "description")
    private String description;

    @Getter @Setter @Column(name = "image")
    private String img;


    public Experience(){}

    public Experience(long id, String title, String description, String img){

        this.id = id;

        this.title = title;

        this.description = description;

        this.img = img;
    }

    @Override
    public Experience clone() {
        
        return new Experience(id, title, description, img);
    }
}
