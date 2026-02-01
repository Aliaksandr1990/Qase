package models;


import models.request.project.ProjectRequestModel;
import com.github.javafaker.Faker;
import tests.api.steps.ProjectSteps;

public class CreateProjectFactory {

    static Faker faker = new Faker();
    public ProjectRequestModel project = CreateProjectFactory.getRandomData();

    public static ProjectRequestModel getRandomData() {
        return ProjectRequestModel.builder()
                .title(faker.name().name())
                .code(faker.bothify("???"))
                .description(faker.lorem().sentence())
                .build();
    }

    public static ProjectRequestModel getWrongRandomData() {
        return ProjectRequestModel.builder()
                .title(faker.name().name())
                .code(faker.regexify("[$$&#]{6}"))
                .build();
    }

    public void createProject() {
        ProjectSteps.createProject(project);
    }

    public void deleteProject(String projectCode) {
        ProjectSteps.deleteProject(projectCode);
    }
}
