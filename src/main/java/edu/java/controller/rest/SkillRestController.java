package edu.java.controller.rest;

import com.google.gson.Gson;
import edu.java.model.Skill;
import edu.java.model.dto.SkillDto;
import edu.java.service.ISkillService;
import edu.java.service.impl.SkillServiceImpl;
import edu.java.util.SkillDtoMapper;

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

@WebServlet(urlPatterns = "/api/v1/skills")
public class SkillRestController extends HttpServlet {
    private ISkillService skillService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        this.skillService = new SkillServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id == null) {
            List<Skill> skills = this.skillService.getList();
            List<SkillDto> skillsDto = skills.stream()
                    .map(skill -> SkillDtoMapper.toSkillDto(skill))
                    .collect(Collectors.toList());
            String skillsJson = this.gson.toJson(skillsDto);
            out.write(skillsJson);
        } else {
            Skill skill = this.skillService.getById(Long.parseLong(id));
            SkillDto skillDto = SkillDtoMapper.toSkillDto(skill);
            String skillJson = this.gson.toJson(skillDto);
            out.write(skillJson);
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
        SkillDto skillDto = this.gson.fromJson(json.toString(), SkillDto.class);
        Skill skill = SkillDtoMapper.toSkill(skillDto);
        Long id = this.skillService.add(skill);
        skillDto.setId(id);
        String response = this.gson.toJson(skillDto);
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
        SkillDto skillDto = this.gson.fromJson(json.toString(), SkillDto.class);
        Skill skill = SkillDtoMapper.toSkill(skillDto);
        this.skillService.update(skill);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        this.skillService.remove(Long.parseLong(id));
        resp.setStatus(200);
    }
}
