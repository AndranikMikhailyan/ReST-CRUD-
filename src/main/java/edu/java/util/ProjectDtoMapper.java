package edu.java.util;

import edu.java.dao.ICustomerDao;
import edu.java.dao.ITeamDao;
import edu.java.dao.hibernate.CustomerDaoImpl;
import edu.java.dao.hibernate.TeamDaoImpl;
import edu.java.model.Project;
import edu.java.model.dto.ProjectDto;

import java.util.Set;
import java.util.stream.Collectors;

public class ProjectDtoMapper {

    private static ITeamDao teamDao;
    private static ICustomerDao customerDao;

    static {
        teamDao = new TeamDaoImpl();
        customerDao = new CustomerDaoImpl();
    }

    public static ProjectDto toProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setBadget(project.getBadget());
        projectDto.setTeamsId(project.getTeams().stream().map(team -> team.getId()).collect(Collectors.toSet()));
        projectDto.setCustomerId(project.getCustomer().getId());
        return projectDto;
    }

    public static Project toProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setBadget(projectDto.getBadget());
        Set<Long> teamsId = projectDto.getTeamsId();
        if (teamsId != null) {
            project.setTeams(teamsId.stream().map(aLong -> teamDao.getById(aLong)).collect(Collectors.toSet()));
        }
        Long customerId = projectDto.getId();
        if (customerId != null) {
            project.setCustomer(customerDao.getById(customerId));
        }
        return project;
    }
}
