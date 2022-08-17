package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.dao.SkillsDao;
import main.models.Skill;


@RestController @RequestMapping("/api/skills")
public class SkillsController {

    @Autowired
    private SkillsDao skillsDao;

    //-> GET
    @RequestMapping(value = "/get")
    public List<Skill> getSkills(){

        return skillsDao.getSkills();
    }

    //-> DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteSkills(@PathVariable long id){

        try {
            skillsDao.deleteSkill(id);
            
        } catch (Exception e) {

            return "No se pudo eliminar la Skill: " + e.getMessage();
        }

        return "Skill: " + id + " eliminada";
    }

    //-> CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Skill createSkills(@RequestBody Skill skill){

        skillsDao.createSkill(skill);

        return skill;
    }

    //-> UPDATE
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Skill updateSkills(@PathVariable Long id, @RequestBody Skill skill){

        skillsDao.updateSkill(id, skill);

        return skill;
    }
}
