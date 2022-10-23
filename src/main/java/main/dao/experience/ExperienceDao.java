package main.dao.experience;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import main.models.Experience;

public interface ExperienceDao {
    
    List<Experience> getExperience();

    Experience deleteExperience(long id);

    Experience createExperience(Experience experience);

    Experience updateExperience(Long id, Experience experience);

    Experience uploadFile(Long id, MultipartFile multipartFile);
}
