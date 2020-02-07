package edu.java.controller.rest;

import com.google.gson.Gson;
import edu.java.model.Project;
import edu.java.model.dto.ProjectDto;
import edu.java.service.IProjectService;
import edu.java.service.impl.ProjectServiceImpl;
import edu.java.util.ProjectDtoMapper;

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

@WebServlet(urlPatterns = "/api/v1/projects")
public class ProjectRestController extends HttpServlet {

    private IProjectService projectService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        this.projectService = new ProjectServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id == null) {
            List<Project> projects = this.projectService.getList();
            List<ProjectDto> projectsDto = projects.stream()
                    .map(project -> ProjectDtoMapper.toProjectDto(project))
                    .collect(Collectors.toList());
            String projectsJson = this.gson.toJson(projectsDto);
            out.write(projectsJson);
        } else {
            Project project = this.projectService.getById(Long.parseLong(id));
            ProjectDto projectDto = ProjectDtoMapper.toProjectDto(project);
            String projectJson = this.gson.toJson(projectDto);
            out.write(projectJson);
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
        ProjectDto projectDto = this.gson.fromJson(json.toString(), ProjectDto.class);
        Project project = ProjectDtoMapper.toProject(projectDto);
        Long id = this.projectService.add(project);
        projectDto.setId(id);
        String response = this.gson.toJson(projectDto);
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
        ProjectDto projectDto = this.gson.fromJson(json.toString(), ProjectDto.class);
        Project project = ProjectDtoMapper.toProject(projectDto);
        this.projectService.update(project);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        this.projectService.remove(Long.parseLong(id));
        resp.setStatus(200);
    }
}