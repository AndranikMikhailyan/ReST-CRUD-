package edu.java.dao.hibernate;

import edu.java.dao.GenericDao;
import edu.java.dao.HibernateUtil;
import edu.java.dao.ITeamDao;
import edu.java.model.Team;
import org.hibernate.Session;

import java.util.List;

public class TeamDaoImpl implements ITeamDao {
    @Override
    public void add(Team entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Team entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Team team = session.get(Team.class, aLong);
        session.delete(team);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Team getById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Team team = session.get(Team.class, aLong);
        session.close();
        return team;
    }

    @Override
    public List<Team> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Team> teams = session.createQuery("FROM Team", Team.class).list();
        session.close();
        return teams;
    }
}
