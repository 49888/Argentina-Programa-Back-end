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
    private Integer percentage;

    @Getter @Setter @Column(name = "image")    
    private String img;


    public Skill(){}

    public Skill(long id, String title, int percentage, String img){

        this.id = id;

        this.title = title;

        this.percentage = percentage;

        this.img = img;
    }

    @Override
    public Skill clone() {
        
        return new Skill(id, title, percentage, img);
    }
}
