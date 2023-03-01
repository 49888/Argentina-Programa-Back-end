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

import main.dao.experience.ExperienceDao;
import main.models.Experience;


@RestController @RequestMapping("/api/experience") @CrossOrigin(origins = {"https://49888.github.io", "https://argentina-programa-abb9b.web.app", "https://www.test-cors.org", "*"})
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

    //Upload Images
    @RequestMapping(value = "/images/update", method = RequestMethod.POST)
    public Experience updateImage(@RequestParam("id") long id, @RequestParam("file") MultipartFile multipartFile){

        return experienceDao.uploadFile(id, multipartFile);
    }
}
