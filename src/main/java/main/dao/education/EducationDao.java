package main.dao.education;

import java.util.List;

import main.models.Education;

public interface EducationDao {
    
    List<Education> getEducation();

    Education deleteEducation(long id);

    Education createEducation(Education education);

    Education updateEducation(Long id, Education education);
}
