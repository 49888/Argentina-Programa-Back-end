package main.dao.skills;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



import main.models.Skill;
import main.services.FileService;


@Repository @Transactional
public class SkillsDaoImp implements SkillsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    FileService fileService;

    @Override
    public List<Skill> getSkills() {

        String query = "FROM Skill";

        List<Skill> result = entityManager.createQuery(query).getResultList();
        
        return result;
    }
    
    @Override
    public Skill deleteSkill(Long id) {
        
        Skill skill = entityManager.find(Skill.class, id);

        Skill aux = skill.clone();

        entityManager.remove(skill);

        return aux;
    }

    @Override
    public Skill createSkill(Skill skill) {

        Skill aux = entityManager.merge(skill).clone();


        List<Skill> result = entityManager.createQuery("FROM Skill").getResultList();

        Long id = 0l;
        
        Iterator<Skill> iterable = result.iterator();

        while(iterable.hasNext()){

            Skill item = iterable.next();

            if(item.getId() > id) id = item.getId();
        }

        aux.setId(id);

        return aux;

    }

    @Override
    public Skill updateSkill(Long id, Skill aux) {
        
        Skill skill = entityManager.find(Skill.class, id);

        if(skill != null) {

            if(aux.getTitle() != null) skill.setTitle(aux.getTitle());

            if(aux.getPercentage() != -1) skill.setPercentage(aux.getPercentage());

            if(aux.getImg() != null) skill.setImg(aux.getImg());

            return skill.clone();
        }
        else {

            return null;
        }

        
    }


    @Override
    public Skill uploadFile(Long id,  MultipartFile multipartFile) {

        String url = null;
        
        try {
            
            String fileName = multipartFile.getOriginalFilename();

            fileName = UUID.randomUUID().toString().concat(fileService.getExtension(fileName));

            File file = fileService.convertToFile(multipartFile, fileName);

            url = fileService.uploadFile(file, fileName);


        }
        catch (Exception e) {
            url = e.getMessage();
        }

        Skill skill = null;

        if(url != null){
            skill = entityManager.find(Skill.class, id);

            skill.setImg(url);
        }

        return skill;
    }
}
