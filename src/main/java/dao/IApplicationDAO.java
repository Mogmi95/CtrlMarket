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
public interface IApplicationDAO {

    public void add(entity.Application a);

    public void update(String oldName, entity.Application a);

    public void remove(entity.Application a);

    public List<entity.Application> findAll();

    public List<entity.Application> searchByName(String name);
    
    public entity.Application get(String name);
}
