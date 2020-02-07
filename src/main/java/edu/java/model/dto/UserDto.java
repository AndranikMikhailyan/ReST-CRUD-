package edu.java.model.dto;

import java.util.Set;

public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String specialty;

    private Long teamId;

    private Set<Long> skillsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Set<Long> getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(Set<Long> skillsId) {
        this.skillsId = skillsId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", teamId=" + teamId +
                ", skillsId=" + skillsId +
                '}';
    }
}
