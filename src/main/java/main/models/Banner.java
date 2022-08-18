package main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity @Table(name = "banner")
public class Banner {

    @Id @Getter @Setter @Column(name = "id")
    private long id;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "title")
    private String title;

    @Getter @Setter @Column(name = "information")
    private String information;

    @Getter @Setter @Column(name = "perfil_image")
    private String perfilImg;

    @Getter @Setter @Column(name = "banner_image")
    private String bannerImg;

    
    public Banner(){}

    public Banner(long id, String name, String title, String information, String perfilImg, String bannerImg) {
        
        this.id = id;

        this.name = name;

        this.title = title;

        this.information = information;

        this.perfilImg = perfilImg;

        this.bannerImg = bannerImg;
    }

    @Override
    public Banner clone() {
        
        return new Banner(id, name, title, information, perfilImg, bannerImg);
    }
}
