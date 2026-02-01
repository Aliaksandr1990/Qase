package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import models.CreateProjectFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;
import pages.ProjectPage;
import tests.api.steps.ProjectSteps;

public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectPage projectPage;
    protected CreateProjectFactory projectFactory;

    @BeforeAll
    public static void configuration() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.pollingInterval = 200;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        projectPage = new ProjectPage();
        projectFactory = new CreateProjectFactory();
    }
}
