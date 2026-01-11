package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;
import pages.ProjectPage;

public class BaseTest {

    LoginPage loginPage;
    ProjectPage projectPage;

    @BeforeAll
    public static void before(){
        System.out.println("start test");
    }

    @BeforeEach
    public void setUp(){
        Configuration.browserSize = "1920x1080";

        loginPage = new LoginPage();
        projectPage = new ProjectPage();
    }

    @AfterAll
    public static void after(){
        System.out.println("end test");
    }
}
