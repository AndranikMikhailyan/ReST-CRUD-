package edu.java.model.dto;

import java.util.Set;

public class TeamDto {

    private Long id;

    private String name;

    private Set<Long> usersId;

    private Set<Long> projectsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getUsersId() {
        return usersId;
    }

    public void setUsersId(Set<Long> usersId) {
        this.usersId = usersId;
    }

    public Set<Long> getProjectsId() {
        return projectsId;
    }

    public void setProjectsId(Set<Long> projectsId) {
        this.projectsId = projectsId;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", usersId=" + usersId +
                ", projectsId=" + projectsId +
                '}';
    }
}
