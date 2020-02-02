package edu.java.controller.rest;

import edu.java.model.Skill;
import edu.java.service.ISkillService;
import edu.java.service.impl.SkillServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/skill")
public class SkillRestController {

    private ISkillService skillService;

    public SkillRestController() {
        this.skillService = new SkillServiceImpl();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSkill(@PathParam("id") Long id) {
        return Response.status(200).entity(skillService.getById(id)).build();
    }

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Skill> getAllSkill() {
        return skillService.getList();
    }

    @POST
    @Path("/create-skill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSkill(Skill skill) {
        skillService.add(skill);
        return Response.status(200).entity(skill).build();
    }
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSkill(@QueryParam("id") Long id) {
        Skill skillServiceById = skillService.getById(id);
        skillService.remove(id);
        return Response.status(200).entity(skillServiceById).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSkill(Skill skill) {
        skillService.update(skill);
        Skill skillServiceById = skillService.getById(skill.getId());
        return Response.status(200).entity(skillServiceById).build();
    }
}
