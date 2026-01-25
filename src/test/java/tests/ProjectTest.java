package tests;

import io.qameta.allure.*;
import models.CreateProjectFactory;
import models.ProjectWindow;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;

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
    @DisplayName("Проверка создания нового проекта")
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

        ProjectWindow createProject = CreateProjectFactory.getRandomData();
        String expectedProjectCode = createProject.getProjectCode();

        projectPage.createProject(createProject)
                .checkRadioButtonPrivate()
                .chekRadioButtonPublic()
                .clickSaveProjectButton();

        step("Убедиться в корректности результата", () ->
                projectPage.checkThatTheProjectHasBeenCreated(expectedProjectCode));
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

        ProjectWindow notCreateProject = CreateProjectFactory.getWrongRandomData();

        projectPage.createProject(notCreateProject)
                .checkRadioButtonPrivate()
                .chekRadioButtonPublic()
                .clickSaveProjectButton();

        step("Ожидаемый результат: получено сообщение об ошибке", () ->
                projectPage.checkThatTheProjectHasBeenNotCreated()
        );
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

        ProjectWindow createProject = CreateProjectFactory.getRandomData();
        String expectedProjectCode = createProject.getProjectCode();

        projectPage.createProject(createProject)
                .checkRadioButtonPrivate()
                .chekRadioButtonPublic()
                .clickSaveProjectButton();

        step("Убедиться в корректности результата", () ->
                projectPage.checkThatTheProjectHasBeenCreated(expectedProjectCode)
        );

        projectPage.openProjectPage();
        projectPage.deleteCreatedProject();
    }
}
