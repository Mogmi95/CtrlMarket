/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author dev
 */
public interface IUserDAO {

    public Boolean add(entity.User u);

    public Boolean update(String oldName, entity.User u);

    public Boolean remove(entity.User u);

    public Boolean isAdmin(entity.User u);
    
    public List<entity.User> findAll();
    
    public List<entity.User> searchByLogin(String login);
        
    public entity.User get(String login);
}
