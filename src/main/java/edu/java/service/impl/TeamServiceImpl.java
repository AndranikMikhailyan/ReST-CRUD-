package edu.java.service.impl;

import edu.java.dao.ITeamDao;
import edu.java.dao.hibernate.TeamDaoImpl;
import edu.java.model.Team;
import edu.java.service.ITeamService;

import java.util.List;

public class TeamServiceImpl implements ITeamService {

    private ITeamDao teamDao;

    public TeamServiceImpl() {
        this.teamDao = new TeamDaoImpl();
    }

    @Override
    public Long add(Team entity) {
        return teamDao.add(entity);
    }

    @Override
    public void update(Team entity) {
        teamDao.update(entity);
    }

    @Override
    public void remove(Long aLong) {
        teamDao.remove(aLong);
    }

    @Override
    public Team getById(Long aLong) {
        return teamDao.getById(aLong);
    }

    @Override
    public List<Team> getList() {
        return teamDao.getList();
    }
}
