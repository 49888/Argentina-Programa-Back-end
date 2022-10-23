package main.dao.skills;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import main.models.Skill;

public interface SkillsDao {
    
    List<Skill> getSkills();

    Skill deleteSkill(Long id);

    Skill createSkill(Skill skill);

    Skill updateSkill(Long id, Skill skill);

    Skill uploadFile(Long id,  MultipartFile multipartFile);
}
