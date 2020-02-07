package edu.java.controller.rest;

import com.google.gson.Gson;
import edu.java.model.Customer;
import edu.java.model.dto.CustomerDto;
import edu.java.service.ICustomerService;
import edu.java.service.impl.CustomerServiceImpl;
import edu.java.util.CustomerDtoMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/v1/customers")
public class CustomerRestController extends HttpServlet {

    private ICustomerService customerService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        this.customerService = new CustomerServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id == null) {
            List<Customer> customers = this.customerService.getList();
            List<CustomerDto> customersDto = customers.stream()
                    .map(customer -> CustomerDtoMapper.toCustomerDto(customer))
                    .collect(Collectors.toList());
            String customersJson = this.gson.toJson(customersDto);
            out.write(customersJson);
        } else {
            Customer customer = this.customerService.getById(Long.parseLong(id));
            CustomerDto customerDto = CustomerDtoMapper.toCustomerDto(customer);
            String customerJson = this.gson.toJson(customerDto);
            out.write(customerJson);
        }
        resp.setStatus(200);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        BufferedReader in = req.getReader();
        StringBuilder json = new StringBuilder();
        in.lines().forEach(s -> json.append(s));
        CustomerDto customerDto = this.gson.fromJson(json.toString(), CustomerDto.class);
        Customer customer = CustomerDtoMapper.toCustomer(customerDto);
        Long id = this.customerService.add(customer);
        customerDto.setId(id);
        String response = this.gson.toJson(customerDto);
        resp.setStatus(200);
        out.write(response);
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        BufferedReader in = req.getReader();
        StringBuilder json = new StringBuilder();
        in.lines().forEach(s -> json.append(s));
        CustomerDto customerDto = this.gson.fromJson(json.toString(), CustomerDto.class);
        Customer customer = CustomerDtoMapper.toCustomer(customerDto);
        this.customerService.update(customer);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        this.customerService.remove(Long.parseLong(id));
        resp.setStatus(200);
    }
}
