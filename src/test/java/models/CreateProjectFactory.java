package models;

import com.github.javafaker.Faker;

public class CreateProjectFactory {

    static Faker faker = new Faker();

    public static ProjectWindow getRandomData() {
        return ProjectWindow.builder()
                .projectName(faker.name().name())
                .projectCode(faker.bothify("???"))
                .projectDescription(faker.lorem().sentence())
                .build();
    }
    public static ProjectWindow getWrongRandomData() {
        return ProjectWindow.builder()
                .projectName(faker.name().name())
                .projectCode(faker.regexify("[$$&#]{6}"))
                .build();
    }
}
