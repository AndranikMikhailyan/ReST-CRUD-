package edu.java.util;

import edu.java.dao.ISkillDao;
import edu.java.dao.ITeamDao;
import edu.java.dao.hibernate.SkillDaoImpl;
import edu.java.dao.hibernate.TeamDaoImpl;
import edu.java.model.User;
import edu.java.model.dto.UserDto;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoMapper {

    private static ITeamDao teamDao;
    private static ISkillDao skillDao;

    static {
        teamDao = new TeamDaoImpl();
        skillDao = new SkillDaoImpl();
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setSpecialty(user.getSpecialty());
        userDto.setTeamId(user.getTeam().getId());
        userDto.setSkillsId(user.getSkills().stream().map(skill -> skill.getId()).collect(Collectors.toSet()));
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setSpecialty(userDto.getSpecialty());
        Long teamId = userDto.getTeamId();
        if (teamId != null) {
            user.setTeam(teamDao.getById(teamId));
        }
        Set<Long> skillsId = userDto.getSkillsId();
        if (skillsId != null) {
            user.setSkills(skillsId.stream().map(aLong -> skillDao.getById(aLong)).collect(Collectors.toSet()));
        }
        return user;
    }
}
