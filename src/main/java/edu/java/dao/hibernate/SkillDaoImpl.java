package edu.java.dao.hibernate;

import edu.java.dao.HibernateUtil;
import edu.java.dao.ISkillDao;
import edu.java.model.Skill;
import org.hibernate.Session;

import java.util.List;

public class SkillDaoImpl implements ISkillDao {
    @Override
    public void add(Skill entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Skill entity) {
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
        Skill skill = session.get(Skill.class, aLong);
        session.delete(skill);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Skill getById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Skill skill = session.get(Skill.class, aLong);
        session.close();
        return skill;
    }

    @Override
    public List<Skill> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Skill> skills = session.createQuery("FROM Skill", Skill.class).list();
        session.close();
        return skills;
    }
}
