package main.dao.education;

import java.util.List;

import main.models.Education;

public interface EducationDao {
    
    List<Education> getEducation();

    void deleteEducation(long id);

    void createEducation(Education education);

    Education updateEducation(Long id, Education education);
}
