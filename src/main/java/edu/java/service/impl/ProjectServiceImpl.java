package edu.java.service.impl;

import edu.java.dao.IProjectDao;
import edu.java.dao.hibernate.ProjectDaoImpl;
import edu.java.model.Project;
import edu.java.service.IProjectService;

import java.util.List;

public class ProjectServiceImpl implements IProjectService {

    private IProjectDao projectDao;

    public ProjectServiceImpl() {
        this.projectDao = new ProjectDaoImpl();
    }

    @Override
    public Long add(Project entity) {
        return projectDao.add(entity);
    }

    @Override
    public void update(Project entity) {
        projectDao.update(entity);
    }

    @Override
    public void remove(Long aLong) {
        projectDao.remove(aLong);
    }

    @Override
    public Project getById(Long aLong) {
        return projectDao.getById(aLong);
    }

    @Override
    public List<Project> getList() {
        return projectDao.getList();
    }
}
