package tests.api.steps;

import io.qameta.allure.Step;
import models.request.project.ProjectRequestModel;
import models.responce.project.EntitiesItem;
import models.responce.project.ErrorWhileCreateProjectWithInvalidData;
import models.responce.project.ProjectCreateResponseModel;
import models.responce.project.ProjectDeleteResponseModel;

import java.util.List;

import static io.restassured.RestAssured.given;
import static tests.api.specs.QASESpec.REQ_SPEC;
import static tests.api.specs.QASESpec.responseWithStatusCode;

public class ProjectSteps {

    static String path = "/project";

    @Step("Создать проект с рандомными данными")
    public static ProjectCreateResponseModel createProject(ProjectRequestModel projectRequest) {
        return given()
                .spec(REQ_SPEC)
                .body(projectRequest)
                .post(path)
                .then()
                .spec(responseWithStatusCode(200))
                .extract().as(ProjectCreateResponseModel.class);
    }

    @Step("Удалить проект с кодом {0}")
    public static ProjectDeleteResponseModel deleteProject(String projectCode) {
        return given()
                .spec(REQ_SPEC)
                .delete(path + "/" + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(200))
                .extract().as(ProjectDeleteResponseModel.class);
    }

    @Step("Получить проект из списка")
    public static List<EntitiesItem> getListProject() {
        final String JSON_PATH = "result.entities";
        return given()
                .spec(REQ_SPEC)
                .get(path)
                .then()
                .spec(responseWithStatusCode(200))
                .extract()
                .jsonPath()
                .getList(JSON_PATH, EntitiesItem.class);
    }

    @Step("Создать проект с невалидными данными и получить сообщение об ошибке")
    public static ErrorWhileCreateProjectWithInvalidData
    createProjectExpectingError(ProjectRequestModel invalidData) {
        return given()
                .spec(REQ_SPEC)
                .body(invalidData)
                .post(path)
                .then()
                .spec(responseWithStatusCode(400))
                .extract().as(ErrorWhileCreateProjectWithInvalidData.class);
    }
}
