package main.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.dao.experience.ExperienceDao;
import main.models.Experience;


@RestController @RequestMapping("/api/experience")
public class ExperienceController {
    
    @Autowired
    private ExperienceDao experienceDao;

    //-> GET
    @RequestMapping(value = "/get")
    public List<Experience> getExperience(){

        List<Experience> list = experienceDao.getExperience();
        
        return list;
    }

    //-> DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteExperience(@PathVariable long id){

        Experience experience = null;

        try {
            experience = experienceDao.deleteExperience(id);
            
        } catch (Exception e) {

            return new ResponseEntity<String>("not found id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Experience>(experience, HttpStatus.OK);
    }

    //-> CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Experience createExperience(@RequestBody Experience experience){

        return experienceDao.createExperience(experience);
    }

    //-> UPDATE
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Experience updateExperience(@PathVariable Long id, @RequestBody Experience experience){

        return experienceDao.updateExperience(id, experience);
    }
}
