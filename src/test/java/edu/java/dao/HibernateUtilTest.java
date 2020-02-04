package edu.java.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class HibernateUtilTest {

    @Test
    public void test() {
        HibernateUtil.getSessionFactory();
    }

}