/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.Collection;
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
    public void add(User u) {
        entityManager.persist(u);
        entityManager.close();
    }

    @Override
    @Transactional(readOnly = false)
    public void update(String oldLogin, User u) {
        Query q = entityManager.createQuery("update User set"
                + "login = " + u.getLogin()
                + "password = " + u.getPassword()
                + "admin = " + u.getAdmin()
                + "where login = " + oldLogin);
        q.executeUpdate();
        entityManager.close();
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(User u) {
        Query q = entityManager.createQuery("delete from User where"
                + "login = " + u.getLogin());
        q.executeUpdate();
        entityManager.close();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> findAll() {
        Query q = entityManager.createQuery("select u from User");
        List l = q.getResultList();
        entityManager.close();

        return l;
    }

    @Override
    @Transactional(readOnly = true)
    public User findWithLogin(String login) {
        Query q = entityManager.createQuery("select u from User where"
                + "login = " + login);
        User u = (User) q.getSingleResult();
        entityManager.close();

        return u;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isAdmin(User u) {
        Query q = entityManager.createQuery("select u from User where"
                + "login = " + u.getLogin()
                + "and password = " + u.getPassword());
        u = (User) q.getSingleResult();
        entityManager.close();
        
        return u.getAdmin();
    }
}
