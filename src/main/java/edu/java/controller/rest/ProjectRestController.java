package edu.java.controller.rest;

import edu.java.model.Project;
import edu.java.service.IProjectService;
import edu.java.service.impl.ProjectServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/project")
public class ProjectRestController {

    private IProjectService projectService;

    public ProjectRestController() {
        this.projectService = new ProjectServiceImpl();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProject(@PathParam("id") Long id) {
        return Response.status(200).entity(projectService.getById(id)).build();
    }

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAllProject() {
        return projectService.getList();
    }

    @POST
    @Path("/create-project")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(Project project) {
        projectService.add(project);
        return Response.status(200).entity(project).build();
    }
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProject(@QueryParam("id") Long id) {
        Project projectServiceById = projectService.getById(id);
        projectService.remove(id);
        return Response.status(200).entity(projectServiceById).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(Project project) {
        projectService.update(project);
        Project projectServiceById = projectService.getById(project.getId());
        return Response.status(200).entity(projectServiceById).build();
    }

}