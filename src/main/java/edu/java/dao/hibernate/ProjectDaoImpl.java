package edu.java.dao.hibernate;

import edu.java.dao.HibernateUtil;
import edu.java.dao.IProjectDao;
import edu.java.model.Project;
import org.hibernate.Session;

import java.util.List;

public class ProjectDaoImpl implements IProjectDao {
    @Override
    public Long add(Project entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Long id = (Long) session.save(entity);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public void update(Project entity) {
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
        Project project = session.get(Project.class, aLong);
        session.delete(project);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Project getById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Project project = session.get(Project.class, aLong);
        session.close();
        return project;
    }

    @Override
    public List<Project> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Project> projects = session.createQuery("FROM Project", Project.class).list();
        session.close();
        return projects;
    }
}
