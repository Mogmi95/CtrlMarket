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
    public Boolean add(Application a) {
        if (get(a.getName()) == null) {
            entityManager.persist(a);
            entityManager.close();
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean update(String oldName, Application a) {
        Application old = get(oldName);

        if (old != null) {
            old.setDescription(a.getDescription());
            old.setName(a.getName());
            old.setLink(a.getLink());
            entityManager.close();
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean remove(Application a) {
        if (get(a.getName()) != null) {
            entityManager.remove(a);
            entityManager.close();
            return true;
        }
        return false;
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
        List l = q.getResultList();
        entityManager.close();

        if (!l.isEmpty()) {
            return (Application) l.get(0);
        } else {
            return null;
        }
    }
}
