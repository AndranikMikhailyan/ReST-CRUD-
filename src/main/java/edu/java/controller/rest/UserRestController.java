package edu.java.controller.rest;

import com.google.gson.Gson;
import edu.java.model.Customer;
import edu.java.model.User;
import edu.java.model.dto.CustomerDto;
import edu.java.model.dto.UserDto;
import edu.java.service.IUserService;
import edu.java.service.impl.CustomerServiceImpl;
import edu.java.service.impl.UserServiceImpl;
import edu.java.util.CustomerDtoMapper;
import edu.java.util.UserDtoMapper;

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

@WebServlet(urlPatterns = "/api/v1/users")
public class UserRestController extends HttpServlet {

    private IUserService userService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id == null) {
            List<User> users = this.userService.getList();
            List<UserDto> usersDto = users.stream()
                    .map(user -> UserDtoMapper.toUserDto(user))
                    .collect(Collectors.toList());
            String usersJson = this.gson.toJson(usersDto);
            out.write(usersJson);
        } else {
            User user = this.userService.getById(Long.parseLong(id));
            UserDto userDto = UserDtoMapper.toUserDto(user);
            String UserJson = this.gson.toJson(userDto);
            out.write(UserJson);
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
        UserDto userDto = this.gson.fromJson(json.toString(), UserDto.class);
        User user = UserDtoMapper.toUser(userDto);
        Long id = this.userService.add(user);
        userDto.setId(id);
        String response = this.gson.toJson(userDto);
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
        UserDto userDto = this.gson.fromJson(json.toString(), UserDto.class);
        User user = UserDtoMapper.toUser(userDto);
        this.userService.update(user);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        this.userService.remove(Long.parseLong(id));
        resp.setStatus(200);
    }
}
