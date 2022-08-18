package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String deleteEducation(@PathVariable long id){

        try {
            educationDao.deleteEducation(id);
            
        } catch (Exception e) {

            return "No se pudo eliminar: " + e.getMessage();
        }

        return "Education: " + id + " eliminada";
    }

    //-> CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Education createEducation(@RequestBody Education education){

        educationDao.createEducation(education);

        return education;
    }

    //-> UPDATE
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Education updateEducation(@PathVariable Long id, @RequestBody Education education){

        return educationDao.updateEducation(id, education);
    }
}
