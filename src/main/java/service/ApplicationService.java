package service;

import dao.IApplicationDAO;
import dao.IUserDAO;
import java.io.IOException;
import javax.jws.WebService;
import org.codehaus.jackson.map.ObjectMapper;
import entity.Application;
import entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Hello world!
 *
 */
@WebService(endpointInterface = "service.IApplicationService")
public class ApplicationService implements IApplicationService {

    @Autowired
    IApplicationDAO dao;
    @Autowired
    IUserDAO udao;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String searchByName(String name) {
        List<Application> l = dao.searchByName(name);

        try {
            return mapper.writeValueAsString(l);
        } catch (IOException ex) {
            return "{ \"status\":\"KO\" }";
        }
    }

    @Override
    public String add(String name, String desc, String link, String user, String cred) {
        if (!udao.isAdmin(cred)) {
            return "{ \"status\":\"KO\" }";
        }

        Application a = new Application();
        User u;

        u = udao.get(user);
        if (u == null) {
            return "{ \"status\":\"KO\" }";
        }

        a.setName(name);
        a.setDescription(desc);
        a.setLink(link);
        a.setUser(u);

        if (dao.add(a)) {
            return "{ \"status\":\"OK\" }";
        } else {
            return "{ \"status\":\"KO\" }";
        }

    }

    @Override
    public String findAll() {
        List<Application> l = dao.findAll();

        try {
            return mapper.writeValueAsString(l);
        } catch (IOException ex) {
            return "{ \"status\":\"KO\" }";
        }
    }

    @Override
    public String update(String oldName, String name, String desc, String link, String user, String cred) {
        if (!udao.isAdmin(cred)) {
            return "{ \"status\":\"KO\" }";
        }

        Application a = new Application();
        User u;

        u = udao.get(user);
        if (u == null) {
            return "{ \"status\":\"KO\" }";
        }

        a.setName(name);
        a.setDescription(desc);
        a.setLink(link);
        a.setUser(u);

        if (dao.update(oldName, a)) {
            return "{ \"status\":\"OK\" }";
        } else {
            return "{ \"status\":\"KO\" }";
        }
    }

    @Override
    public String delete(String name, String cred) {
        if (!udao.isAdmin(cred)) {
            return "{ \"status\":\"KO\" }";
        }

        Application a = dao.get(name);

        if (a != null && dao.remove(a)) {
            return "{ \"status\":\"OK\" }";
        } else {
            return "{ \"status\":\"KO\" }";
        }
    }
}
