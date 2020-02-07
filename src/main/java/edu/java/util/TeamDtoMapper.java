package edu.java.util;

import edu.java.dao.IProjectDao;
import edu.java.dao.IUserDao;
import edu.java.dao.hibernate.ProjectDaoImpl;
import edu.java.dao.hibernate.UserDaoImpl;
import edu.java.model.Team;
import edu.java.model.dto.TeamDto;

import java.util.Set;
import java.util.stream.Collectors;

public class TeamDtoMapper {

    private static IProjectDao projectDao;
    private static IUserDao userDao;

    static {
        projectDao = new ProjectDaoImpl();
        userDao = new UserDaoImpl();
    }

    public static TeamDto toTeamDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        teamDto.setProjectsId(team.getProjects().stream().map(project -> project.getId()).collect(Collectors.toSet()));
        teamDto.setUsersId(team.getUsers().stream().map(user -> user.getId()).collect(Collectors.toSet()));
        return teamDto;
    }

    public static Team toTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setId(teamDto.getId());
        team.setName(teamDto.getName());
        Set<Long> usersId = teamDto.getUsersId();
        if (usersId != null) {
            team.setUsers(usersId.stream().map(aLong -> userDao.getById(aLong)).collect(Collectors.toSet()));
        }
        Set<Long> projectsId = teamDto.getProjectsId();
        if (projectsId != null) {
            team.setProjects(projectsId.stream().map(aLong -> projectDao.getById(aLong)).collect(Collectors.toSet()));
        }
        return team;
    }
}
