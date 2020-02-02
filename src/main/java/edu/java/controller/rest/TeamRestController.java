package edu.java.controller.rest;

import edu.java.model.Team;
import edu.java.service.ITeamService;
import edu.java.service.impl.TeamServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/team")
public class TeamRestController {

    private ITeamService teamService;

    public TeamRestController() {
        this.teamService = new TeamServiceImpl();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeam(@PathParam("id") Long id) {
        return Response.status(200).entity(teamService.getById(id)).build();
    }

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> getAllTeam() {
        return teamService.getList();
    }

    @POST
    @Path("/create-team")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTeam(Team team) {
        teamService.add(team);
        return Response.status(200).entity(team).build();
    }
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTeam(@QueryParam("id") Long id) {
        Team teamServiceById = teamService.getById(id);
        teamService.remove(id);
        return Response.status(200).entity(teamServiceById).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeam(Team team) {
        teamService.update(team);
        Team teamServiceById = teamService.getById(team.getId());
        return Response.status(200).entity(teamServiceById).build();
    }
}
