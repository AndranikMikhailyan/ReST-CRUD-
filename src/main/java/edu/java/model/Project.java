package edu.java.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "badget")
    private BigDecimal badget;

    @ManyToMany(mappedBy = "projects",fetch = FetchType.EAGER)
    private Set<Team> teams;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Project() {
    }

    public Project(String name, BigDecimal badget, Set<Team> teams, Customer customer) {
        this.name = name;
        this.badget = badget;
        this.teams = teams;
        this.customer = customer;
    }

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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}






