import edu.java.controller.rest.ProjectRestController;
import edu.java.model.Project;

import javax.ws.rs.core.Response;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ProjectRestController projectRestController = new ProjectRestController();

        List<Project> allProject = projectRestController.getAllProject();

        for (Project project : allProject) {
            System.out.println(project.getId());
            System.out.println(project.getName());
            System.out.println(project.getBadget());
            System.out.println(project.getTeams());
            System.out.println(project.getCustomer().getId());
//        }

            ProjectRestController prc = new ProjectRestController();

//        List<Project> allProject1 = prc.getAllProject();
//        for (Project project : allProject1) {
//            System.out.println(project.getId());
//            System.out.println(project.getName());
//            System.out.println(project.getBadget());
//            System.out.println(project.getTeams());
//            System.out.println(project.getCustomer().getId());
//            project.getCustomer().getProjects().forEach(project1 -> System.out.println(project1.getId()));
//        }
//        Response response = prc.getProject(4L);
//        Project project = (Project) response.getEntity();
//        System.out.println(project.getId());
//        System.out.println(project.getName());
//        System.out.println(project.getBadget());
//        System.out.println(project.getTeams());
//        System.out.println(project.getCustomer().getId());
        }
    }
}
