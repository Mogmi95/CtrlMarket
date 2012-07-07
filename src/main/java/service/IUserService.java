/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author dev
 */
@WebService
@Path("/")
public interface IUserService {

    @WebMethod(action = "add")
    @GET
    @Path("/add/{login}/{password}")
    @Produces("application/json")
    public String add(@PathParam("login") @WebParam(name = "login") String login,
            @PathParam("password") @WebParam(name = "password") String password);

    @WebMethod(action = "delete")
    @GET
    @Path("/delete/{login}/{cred}")
    @Produces("application/json")
    public String delete(@PathParam("login") @WebParam(name = "login") String login,
            @PathParam("cred") @WebParam(name = "cred") String cred);

    @WebMethod(action = "update")
    @GET
    @Path("/update/{oldlogin}/{login}/{password}/{isadmin}/{cred}")
    @Produces("application/json")
    public String update(@PathParam("oldlogin") @WebParam(name = "oldlogin") String oldlogin,
            @PathParam("login") @WebParam(name = "login") String login,
            @PathParam("password") @WebParam(name = "password") String password,
            @PathParam("isadmin") @WebParam(name = "isadmin") Boolean isadmin,
            @PathParam("cred") @WebParam(name = "cred") String cred);

    @WebMethod(action = "findAll")
    @GET
    @Path("/findAll")
    @Produces("application/json")
    public String findAll();

    @WebMethod(action = "searchByLogin")
    @GET
    @Path("/searchByLogin/{login}")
    @Produces("application/json")
    public String searchByLogin(@PathParam("login") @WebParam(name = "login") String login);
    
    @WebMethod(action = "getAppsForUser")
    @GET
    @Path("/getAppsForUser/{login}")
    @Produces("application/json")
    public String getAppsForUser(@PathParam("login") @WebParam(name = "login") String login);
}
