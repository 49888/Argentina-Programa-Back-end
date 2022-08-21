package main.dao.skills;

import java.util.List;

import main.models.Skill;

public interface SkillsDao {
    
    List<Skill> getSkills();

    Skill deleteSkill(Long id);

    Skill createSkill(Skill skill);

    Skill updateSkill(Long id, Skill skill);
}
