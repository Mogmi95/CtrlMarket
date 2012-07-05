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

    public Boolean add(entity.Application a);

    public Boolean update(String oldName, entity.Application a);

    public Boolean remove(entity.Application a);

    public List<entity.Application> findAll();

    public List<entity.Application> searchByName(String name);
    
    public entity.Application get(String name);
}
