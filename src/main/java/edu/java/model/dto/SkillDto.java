package edu.java.model.dto;

import java.util.Set;

public class SkillDto {

    private Long id;

    private String name;

    private Set<Long> usersId;

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

    @Override
    public String toString() {
        return "SkillDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", usersId=" + usersId +
                '}';
    }
}
