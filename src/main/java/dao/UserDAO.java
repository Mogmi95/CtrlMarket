/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Application;
import entity.User;
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
public class UserDAO implements IUserDAO {

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
    public Boolean add(User u) {
        if (get(u.getLogin()) == null) {
            entityManager.persist(u);
            entityManager.close();
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean update(String oldName, User u) {
        User old = get(oldName);

        if (old != null) {
            old.setAdmin(u.getAdmin());
            old.setLogin(u.getLogin());
            old.setPassword(u.getPassword());
            entityManager.close();
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean remove(User u) {
        if (get(u.getLogin()) != null) {
            entityManager.remove(u);
            entityManager.close();
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isAdmin(User u) {
        u = get(u.getLogin());

        if (u != null) {
            return u.getAdmin();
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        Query q = entityManager.createQuery("from User");
        List l = q.getResultList();
        entityManager.close();

        return l;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> searchByLogin(String login) {
        Query q = entityManager.createQuery("select u from User u where "
                + "login like :name");
        q.setParameter("name", '%' + login + '%');
        List l = q.getResultList();
        entityManager.close();

        return l;
    }

    @Override
    @Transactional(readOnly = true)
    public User get(String login) {
        Query q = entityManager.createQuery("select u from User u where "
                + "login = :name");
        q.setParameter("name", login);
        List l = q.getResultList();
        entityManager.close();

        if (!l.isEmpty()) {
            return (User) l.get(0);
        } else {
            return null;
        }
    }
}
