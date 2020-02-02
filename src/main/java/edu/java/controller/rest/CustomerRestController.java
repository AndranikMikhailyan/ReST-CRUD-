package edu.java.controller.rest;

import edu.java.model.Customer;
import edu.java.service.ICustomerService;
import edu.java.service.impl.CustomerServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customer")
public class CustomerRestController {

    private ICustomerService customerService;

    public CustomerRestController() {
        this.customerService = new CustomerServiceImpl();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") Long id) {
        return Response.status(200).entity(customerService.getById(id)).build();
    }

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomer() {
        return customerService.getList();
    }

    @POST
    @Path("/create-customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        customerService.add(customer);
        return Response.status(200).entity(customer).build();
    }
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@QueryParam("id") Long id) {
        Customer customerServiceById = customerService.getById(id);
        customerService.remove(id);
        return Response.status(200).entity(customerServiceById).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) {
        customerService.update(customer);
        Customer customerServiceById = customerService.getById(customer.getId());
        return Response.status(200).entity(customerServiceById).build();
    }
}
