package tests.api.test;

import io.qameta.allure.*;
import models.CreateProjectFactory;
import models.request.project.ProjectRequestModel;
import models.responce.project.EntitiesItem;
import models.responce.project.ErrorWhileCreateProjectWithInvalidData;
import models.responce.project.ProjectCreateResponseModel;
import models.responce.project.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import tests.api.steps.ProjectSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Owner("mkarpovich")
@Feature("Project")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ApiProjectTest extends BaseTest {

    @Test
    @DisplayName("Проверка создания нового проекта с валидными данными")
    @Story("Успешное создание нового проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test")
    })
    void projectMustBeCreatedWithApi() {
        ProjectRequestModel data = CreateProjectFactory.getRandomData();
        ProjectCreateResponseModel response = ProjectSteps.createProject(data);

        assertThat(response)
                .isNotNull()
                .extracting(ProjectCreateResponseModel::getResult)
                .extracting(Result::getCode)
                .isEqualTo(data.getCode().toUpperCase());

        projectFactory.deleteProject(data.getCode());
    }

    @Test
    @DisplayName("Проверка удаления созданного проекта")
    @Story("Успешное удаление  созданного проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test")
    })
    void projectMustBeDeletedWithApi() {
        ProjectRequestModel data = CreateProjectFactory.getRandomData();
        ProjectCreateResponseModel response = ProjectSteps.createProject(data);

        projectFactory.deleteProject(data.getCode());

        List<EntitiesItem> listProjectsCode = ProjectSteps.getListProject();

        assertThat(listProjectsCode)
                .extracting(EntitiesItem::getCode)
                .doesNotContain(data.getCode());
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при вводе некорректных данных при создании проекта")
    @Story("Отображение сообщения об ошибке при вводе некорректных данных в форме создания проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test")
    })
    void projectMustBeNotCreatedWithApi() {
        ProjectRequestModel invalidData = CreateProjectFactory.getWrongRandomData();
        ErrorWhileCreateProjectWithInvalidData errorResponse = ProjectSteps.createProjectExpectingError(invalidData);

        assertThat(errorResponse)
                .isNotNull()
                .extracting(ErrorWhileCreateProjectWithInvalidData::getErrorMessage)
                .isEqualTo(errorResponse.getErrorMessage());
    }
}
