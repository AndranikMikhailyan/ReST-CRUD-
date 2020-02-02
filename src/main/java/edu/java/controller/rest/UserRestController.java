package edu.java.controller.rest;

import edu.java.model.User;
import edu.java.service.IUserService;
import edu.java.service.impl.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserRestController {

    private IUserService userService;

    public UserRestController() {
        this.userService = new UserServiceImpl();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id) {
        return Response.status(200).entity(userService.getById(id)).build();
    }

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUser() {
        return userService.getList();
    }

    @POST
    @Path("/create-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userService.add(user);
        return Response.status(200).entity(user).build();
    }
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("id") Long id) {
        User userServiceById = userService.getById(id);
        userService.remove(id);
        return Response.status(200).entity(userServiceById).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        userService.update(user);
        User userServiceById = userService.getById(user.getId());
        return Response.status(200).entity(userServiceById).build();
    }
}
