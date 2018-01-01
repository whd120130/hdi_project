package com.hdi.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * DAO基类
 * @author 王慧东
 * @date 2017/12/27.
 * @version 1.0
 */
public class BaseDao<T> extends HibernateDaoSupport {
    private final Class<T> entityClass;

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T get(Serializable id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    public List<T> loadAll() {
        return getHibernateTemplate().loadAll(entityClass);
    }

    public Long getTotalCount() {
        String hql = "select count(*) from " + entityClass.getName();
        return (Long) getSession().createQuery(hql).uniqueResult();
    }

    public void add(T entity) {
        getHibernateTemplate().persist(entity);
    }

    public void remove(T entity) {
        getHibernateTemplate().delete(entity);
    }

    public void update(T entity) {
        getHibernateTemplate().merge(entity);
    }

    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    public void refresh(T entity) {
        getHibernateTemplate().refresh(entity);
    }

    public List<?> find(String hql) {
        return getHibernateTemplate().find(hql);
    }

    public List<?> find(String hql, Object... objects) {
        return getHibernateTemplate().find(hql, objects);
    }
}
