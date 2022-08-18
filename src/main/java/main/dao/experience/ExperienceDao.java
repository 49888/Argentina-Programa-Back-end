package main.dao.experience;

import java.util.List;

import main.models.Experience;

public interface ExperienceDao {
    
    List<Experience> getExperience();

    void deleteExperience(long id);

    void createExperience(Experience experience);

    Experience updateExperience(Long id, Experience experience);
}
