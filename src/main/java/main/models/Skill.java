package main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "skills")
public class Skill {

    @Id @Getter @Setter @Column(name = "id")
    private long id;
    
    @Getter @Setter @Column(name = "title")
    private String title;

    @Getter @Setter @Column(name = "percentage")
    private int percentage;

    @Getter @Setter @Column(name = "image")    
    private String imageURL;


    public Skill(){}

    public Skill(String title, int percentage, String imageURL){

        this.title = title;

        this.percentage = percentage;

        this.imageURL = imageURL;
    }
}
