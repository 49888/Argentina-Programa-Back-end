package main.dao.education;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import main.models.Education;
import main.services.FileService;

@Repository @Transactional
public class EducationDaoImp implements EducationDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    FileService fileService;
    
    @Override
    public List<Education> getEducation() {

        String query = "FROM Education";

        List<Education> result = entityManager.createQuery(query).getResultList();
        
        return result;
    }

    @Override
    public Education createEducation(Education education) {
        
        Education aux = entityManager.merge(education).clone();


        List<Education> result = entityManager.createQuery("FROM Education").getResultList();

        Long id = 0l;
        
        Iterator<Education> iterable = result.iterator();

        while(iterable.hasNext()){

            Education item = iterable.next();

            if(item.getId() > id) id = item.getId();
        }

        aux.setId(id);

        return aux;
    }

    @Override
    public Education deleteEducation(long id) {
        
        Education education = entityManager.find(Education.class, id);

        Education aux = education.clone();

        entityManager.remove(education);

        return aux;
    }

    @Override
    public Education updateEducation(Long id, Education aux) {
        
        Education education = entityManager.find(Education.class, id);

        if(education != null) {

            if(aux.getTitle() != null) education.setTitle(aux.getTitle());

            if(aux.getDescription() != null) education.setDescription(aux.getDescription());

            if(aux.getImg() != null) education.setImg(aux.getImg());

            return education.clone();
        }
        else {

            return null;
        }
    }
    

    @Override
    public Education uploadFile(Long id, MultipartFile multipartFile) {

        String url = null;
        
        try {
            
            String fileName = multipartFile.getOriginalFilename();

            fileName = UUID.randomUUID().toString().concat(fileService.getExtension(fileName));



            url = fileService.uploadFile(multipartFile, fileName);


        }
        catch (Exception e) {
            url = e.getMessage();
        }

        Education education = null;

        if(url != null){
            education = entityManager.find(Education.class, id);

            education.setImg(url);
        }

        return education;
    }
}
