package edu.java.controller.rest;


import com.google.gson.Gson;
import edu.java.model.Team;
import edu.java.model.dto.TeamDto;
import edu.java.service.ITeamService;
import edu.java.service.impl.TeamServiceImpl;
import edu.java.util.TeamDtoMapper;

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

@WebServlet(urlPatterns = "/api/v1/teams")
public class TeamRestController extends HttpServlet {
    private ITeamService teamService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        this.teamService = new TeamServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id == null) {
            List<Team> teams = this.teamService.getList();
            List<TeamDto> teamsDto = teams.stream()
                    .map(team -> TeamDtoMapper.toTeamDto(team))
                    .collect(Collectors.toList());
            String teamsJson = this.gson.toJson(teamsDto);
            out.write(teamsJson);
        } else {
            Team team = this.teamService.getById(Long.parseLong(id));
            TeamDto teamDto = TeamDtoMapper.toTeamDto(team);
            String teamJson = this.gson.toJson(teamDto);
            out.write(teamJson);
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
        TeamDto teamDto = this.gson.fromJson(json.toString(), TeamDto.class);
        Team team = TeamDtoMapper.toTeam(teamDto);
        Long id = this.teamService.add(team);
        teamDto.setId(id);
        String response = this.gson.toJson(teamDto);
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
        TeamDto teamDto = this.gson.fromJson(json.toString(), TeamDto.class);
        Team team = TeamDtoMapper.toTeam(teamDto);
        this.teamService.update(team);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        this.teamService.remove(Long.parseLong(id));
        resp.setStatus(200);
    }
}
