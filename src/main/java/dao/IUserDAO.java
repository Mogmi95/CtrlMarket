/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;

/**
 *
 * @author dev
 */
public interface IUserDAO {

    public void add(entity.User u);

    public void update(String oldName, entity.User u);

    public void remove(entity.User u);

    public Boolean isAdmin(entity.User u);
    
    public Collection<entity.User> findAll();
    
    public entity.User findWithLogin(String login);
}
