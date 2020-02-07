package edu.java.model.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ProjectDto {

    private Long id;

    private String name;

    private BigDecimal badget;

    private Set<Long> teamsId;

    private Long customerId;

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

    public BigDecimal getBadget() {
        return badget;
    }

    public void setBadget(BigDecimal badget) {
        this.badget = badget;
    }

    public Set<Long> getTeamsId() {
        return teamsId;
    }

    public void setTeamsId(Set<Long> teamsId) {
        this.teamsId = teamsId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", badget=" + badget +
                ", teamsId=" + teamsId +
                ", customerId=" + customerId +
                '}';
    }
}
