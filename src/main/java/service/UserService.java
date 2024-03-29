/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IApplicationDAO;
import dao.IUserDAO;
import entity.Application;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.jws.WebService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dev
 */
@WebService(endpointInterface = "service.IUserService")
public class UserService implements IUserService {

    @Autowired
    IApplicationDAO adao;
    @Autowired
    IUserDAO dao;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String add(String login, String password) {
        User a = new User();
        a.setLogin(login);
        a.setPassword(password);

        if (dao.add(a)) {
            return "{ \"status\":\"OK\" }";
        } else {
            return "{ \"status\":\"KO\" }";
        }
    }

    @Override
    public String delete(String login, String cred) {
        if (!dao.isAdmin(cred)) {
            return "{ \"status\":\"KO\" }";
        }

        User a = dao.get(login);

        if (a != null && dao.remove(a)) {
            return "{ \"status\":\"OK\" }";
        } else {
            return "{ \"status\":\"KO\" }";
        }
    }

    @Override
    public String update(String oldlogin, String login, String password, Boolean isadmin, String cred) {
        if (!dao.isAdmin(cred)) {
            return "{ \"status\":\"KO\" }";
        }

        User a = new User();

        a.setLogin(login);
        a.setPassword(password);
        a.setAdmin(isadmin);
        if (dao.update(oldlogin, a)) {
            return "{ \"status\":\"OK\" }";
        } else {
            return "{ \"status\":\"KO\" }";
        }
    }

    @Override
    public String findAll() {
        List<User> l = dao.findAll();

        try {
            return mapper.writeValueAsString(l);
        } catch (IOException ex) {
            return "{ \"status\":\"KO\" }";
        }
    }

    @Override
    public String searchByLogin(String login) {
        List<User> l = dao.searchByLogin(login);

        try {
            return mapper.writeValueAsString(l);
        } catch (IOException ex) {
            return "{ \"status\":\"KO\" }";
        }
    }

    @Override
    public String getAppsForUser(String login) {
        List<Application> l = dao.getApp(login);

        try {
            return mapper.writeValueAsString(l);
        } catch (IOException ex) {
            return "{ \"status\":\"KO\" }";
        }
    }
}
