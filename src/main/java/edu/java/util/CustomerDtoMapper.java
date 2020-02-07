package edu.java.util;

import edu.java.dao.IProjectDao;
import edu.java.dao.hibernate.ProjectDaoImpl;
import edu.java.model.Customer;
import edu.java.model.dto.CustomerDto;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomerDtoMapper {

    private static IProjectDao projectDao;

    static {
        projectDao = new ProjectDaoImpl();
    }

    public static CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setProjectsId(customer.getProjects().stream().map(project -> project.getId()).collect(Collectors.toSet()));
        return customerDto;
    }

    public static Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        Set<Long> projectsId = customerDto.getProjectsId();
        if (projectsId != null) {
            customer.setProjects(projectsId.stream().map(aLong -> projectDao.getById(aLong))
                    .collect(Collectors.toSet()));
        }
        return customer;
    }
}
