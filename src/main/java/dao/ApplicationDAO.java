/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Application;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dev
 */
@Repository
@Transactional(readOnly = false)
public class ApplicationDAO implements IApplicationDAO {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = false)
    public void add(Application a) {
        entityManager.persist(a);
        entityManager.close();
    }

    @Override
    @Transactional(readOnly = false)
    public void update(String oldName, Application a) {
        Query q = entityManager.createQuery("update Application a set "
                + "a.name = :name"
                + "a.description = :desc"
                + "a.link = :link"
                + "where name = :oldName");
        q.setParameter("name", a.getName()).setParameter("desc", a.getDescription()).setParameter("link", a.getLink()).setParameter("oldName", oldName);
        q.executeUpdate();
        entityManager.close();
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Application a) {
        Query q = entityManager.createQuery("delete from Application where "
                + "name = :name");
        q.setParameter("name", a.getName());
        q.executeUpdate();
        entityManager.close();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> findAll() {
        Query q = entityManager.createQuery("from Application");
        List l = q.getResultList();
        entityManager.close();

        return l;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> searchByName(String name) {
        Query q = entityManager.createQuery("select a from Application a where "
                + "name like :name");
        q.setParameter("name", '%' + name + '%');
        List l = q.getResultList();
        entityManager.close();

        return l;
    }

    @Override
    @Transactional(readOnly = true)
    public Application get(String name) {
        Query q = entityManager.createQuery("select a from Application a where "
                + "name = :name");
        q.setParameter("name", name);
        Application a = (Application) q.getSingleResult();
        entityManager.close();

        return a;
    }
}
