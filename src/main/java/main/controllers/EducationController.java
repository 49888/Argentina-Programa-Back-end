package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import main.dao.education.EducationDao;
import main.models.Education;


@RestController @RequestMapping("/api/education")
public class EducationController {
    
    @Autowired
    private EducationDao educationDao;

    //-> GET
    @RequestMapping(value = "/get")
    public List<Education> getEducation(){

        List<Education> list = educationDao.getEducation();
        
        return list;
    }

    //-> DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEducation(@PathVariable long id){

        Education education = null;

        try {
            education = educationDao.deleteEducation(id);
            
        } catch (Exception e) {

            return new ResponseEntity<String>("not found id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Education>(education, HttpStatus.OK);
    }

    //-> CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Education createEducation(@RequestBody Education education){

        return educationDao.createEducation(education);
    }

    //-> UPDATE
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Education updateEducation(@PathVariable Long id, @RequestBody Education education){

        return educationDao.updateEducation(id, education);
    }


    //Upload Images
    @RequestMapping(value = "/images/update", method = RequestMethod.POST)
    public Education updateImage(@RequestParam("id") long id, @RequestParam("file") MultipartFile multipartFile){

        return educationDao.uploadFile(id, multipartFile);
    }
}
