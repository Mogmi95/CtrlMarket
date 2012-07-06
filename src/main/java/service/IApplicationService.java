/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;

/**
 *
 * @author Julien "Roulyo" Fraisse
 */
@WebService
@Path("/")
public interface IApplicationService {

    @WebMethod(action = "searchByName")
    @GET
    @Path("/searchByName/{name}")
    @Produces("application/json")
    public String searchByName(@PathParam("name") @WebParam(name = "name") String name);

    @WebMethod(action = "findAll")
    @GET
    @Path("/findAll")
    @Produces("application/json")
    public String findAll();

    @WebMethod(action = "add")
    @GET
    @Path("/add/{name}/{desc}/{link}")
    @Produces("application/json")
    public String add(@PathParam("name") @WebParam(name = "name") String name,
            @PathParam("desc") @WebParam(name = "desc") String desc,
            @PathParam("link") @WebParam(name = "link") String link,
            @PathParam("user") @WebParam(name = "user") String user);

    @WebMethod(action = "update")
    @GET
    @Path("/update/{oldName}/{name}/{desc}/{link}")
    @Produces("application/json")
    public String update(@PathParam("oldName") @WebParam(name = "oldName") String oldName,
            @PathParam("name") @WebParam(name = "name") String name,
            @PathParam("desc") @WebParam(name = "desc") String desc,
            @PathParam("link") @WebParam(name = "link") String link,
            @PathParam("user") @WebParam(name = "user") String user);

    @WebMethod(action = "delete")
    @GET
    @Path("/delete/{name}")
    @Produces("application/json")
    public String delete(@PathParam("name") @WebParam(name = "name") String name);
}
