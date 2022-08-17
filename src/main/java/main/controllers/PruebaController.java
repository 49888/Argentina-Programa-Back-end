package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import main.dao.SkillsDao;
import main.models.Skill;
import main.models.User;

@RestController
public class PruebaController {

    @Autowired
    private SkillsDao skillsDao;

    @RequestMapping("/skills")
    public List<Skill> getSkills(){

        return skillsDao.getSkills();
    }
    
    @RequestMapping("/prueba")
    public String getMain(){

        System.out.println("Se ejecuta");

        return "Funciona por favor";
    }


    @RequestMapping("/lista")
    public List<String> getList(){

        List<String> list = List.of("Majo", "Javier", "Lucho", "Milagros");

        return list;
    }

    @RequestMapping("/user")
    public User getUser(){

        return new User("Franco", "francogadea00@gmail.com", "Komi1812");
    }
}
