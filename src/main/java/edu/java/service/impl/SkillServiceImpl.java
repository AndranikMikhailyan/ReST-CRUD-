package edu.java.service.impl;

import edu.java.dao.ISkillDao;
import edu.java.dao.hibernate.SkillDaoImpl;
import edu.java.model.Skill;
import edu.java.service.ISkillService;

import java.util.List;

public class SkillServiceImpl implements ISkillService {

    private ISkillDao skillDao;

    public SkillServiceImpl() {
        this.skillDao = new SkillDaoImpl();
    }

    @Override
    public void add(Skill entity) {
        skillDao.add(entity);
    }

    @Override
    public void update(Skill entity) {
        skillDao.update(entity);
    }

    @Override
    public void remove(Long aLong) {
        skillDao.remove(aLong);
    }

    @Override
    public Skill getById(Long aLong) {
        return skillDao.getById(aLong);
    }

    @Override
    public List<Skill> getList() {
        return skillDao.getList();
    }
}
