package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import main.dao.skills.SkillsDao;
import main.models.Skill;
import net.bytebuddy.asm.Advice.Return;


@RestController @RequestMapping("/api/skills") @CrossOrigin(origins = {"https://49888.github.io", "https://argentina-programa-abb9b.web.app", "https://www.test-cors.org", "*"})
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
    public ResponseEntity deleteSkills(@PathVariable long id){

        Skill skill = null;

        try {
            skill = skillsDao.deleteSkill(id);
            
        } catch (Exception e) {

            return new ResponseEntity<String>("not found id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Skill>(skill, HttpStatus.OK);
    }

    //-> CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Skill createSkills(@RequestBody Skill skill){

        
        return skillsDao.createSkill(skill);
    }

    //-> UPDATE
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Skill updateSkills(@PathVariable Long id, @RequestBody Skill skill){

        return skillsDao.updateSkill(id, skill);
    }


    //Upload Images
    @RequestMapping(value = "/images/update", method = RequestMethod.POST)
    public Skill updateImage(@RequestParam("id") long id, @RequestParam("file") MultipartFile multipartFile){

        return skillsDao.uploadFile(id,  multipartFile);
    }
}
