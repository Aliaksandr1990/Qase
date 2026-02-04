package tests.ui;

import io.qameta.allure.*;
import models.CreateProjectFactory;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Owner("mkarpovich")
@Feature("Project")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class ProjectTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        step("Открыть страницу авторизации");
        loginPage.openPage("/login");
    }

    @Test
    @DisplayName("Проверка создания нового проекта с валидными данными")
    @Story("Успешное создание нового проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    public void projectMustBeCreated() {
        loginPage.setValueEmailInput("akytat@mailto.plus")
                .setValuePasswordInput("20091989Qwe!!!")
                .clickSignInButton();

        projectPage.clickCreateProjectButton();

        ProjectRequestModel createProject = CreateProjectFactory.getRandomData();
        String expectedProjectCode = createProject.getCode();

        projectPage.createProject(createProject)
                .checkRadioButtonPrivate()
                .chekRadioButtonPublic()
                .clickSaveProjectButton()
                .checkThatTheProjectHasBeenCreated(expectedProjectCode);

        projectFactory.deleteProject(expectedProjectCode, 200);
    }

    @Test
    @DisplayName("Проверка получения сообщения об ошибке при вводе некорректных данных при создании проекта")
    @Story("Отображение сообщения об ошибке при вводе некорректных данных в форме создания проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    public void projectMustBeNotCreated() {
        loginPage.setValueEmailInput("akytat@mailto.plus")
                .setValuePasswordInput("20091989Qwe!!!")
                .clickSignInButton();

        projectPage.clickCreateProjectButton();

        ProjectRequestModel notCreateProject = CreateProjectFactory.getWrongRandomData();

        projectPage.createProject(notCreateProject)
                .checkRadioButtonPrivate()
                .chekRadioButtonPublic()
                .clickSaveProjectButton()
                .checkThatTheProjectHasBeenNotCreated();
    }

    @Test
    @DisplayName("Проверка удаления созданного проекта")
    @Story("Успешное удаление  созданного проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    public void projectMustBeDeleted() {
        loginPage.setValueEmailInput("akytat@mailto.plus")
                .setValuePasswordInput("20091989Qwe!!!")
                .clickSignInButton();

        projectPage.clickCreateProjectButton();

        ProjectRequestModel createProject = CreateProjectFactory.getRandomData();
        String projectTitle = createProject.getTitle();

        projectPage.createProject(createProject)
                        .clickSaveProjectButton()
                        .openProjectPage()
                        .deleteCreatedProject()
                        .checkThatProjectIsDeleted(projectTitle);
    }
}
