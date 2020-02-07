package edu.java.util;

import edu.java.dao.IUserDao;
import edu.java.dao.hibernate.UserDaoImpl;
import edu.java.model.Skill;
import edu.java.model.dto.SkillDto;

import java.util.Set;
import java.util.stream.Collectors;

public class SkillDtoMapper {

    private static IUserDao userDao;

    static {
        userDao = new UserDaoImpl();
    }

    public static SkillDto toSkillDto(Skill skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getId());
        skillDto.setName(skill.getName());
        skillDto.setUsersId(skill.getUsers().stream().map(user -> user.getId()).collect(Collectors.toSet()));
        return skillDto;
    }

    public static Skill toSkill(SkillDto skillDto) {
        Skill skill = new Skill();
        skill.setId(skillDto.getId());
        skill.setName(skillDto.getName());
        Set<Long> usersId = skillDto.getUsersId();
        if (usersId != null) {
            skill.setUsers(usersId.stream().map(aLong -> userDao.getById(aLong)).collect(Collectors.toSet()));
        }
        return skill;
    }
}
