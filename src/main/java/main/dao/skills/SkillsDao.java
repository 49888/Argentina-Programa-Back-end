package main.dao.skills;

import java.util.List;

import main.models.Skill;

public interface SkillsDao {
    
    List<Skill> getSkills();

    void deleteSkill(Long id);

    void createSkill(Skill skill);

    Skill updateSkill(Long id, Skill skill);
}
