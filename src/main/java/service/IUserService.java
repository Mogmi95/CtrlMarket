/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author dev
 */
@WebService
@Path("/user")
public interface IUserService {
    
    @WebMethod(action = "add")
    @GET
    @Path("add/{login}/{password}")
    @Produces("application/json")
    public String add();
}

