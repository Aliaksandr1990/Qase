package tests.ui;

import config.Credentials;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import tests.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@Owner("mkarpovich")
@Feature("Authorization")
@Link(value = "My_GitHab", url = "https://github.com/krivelevamaari-spec/Qase")
public class LoginTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        step("Открыть страницу авторизации",
                () ->  loginPage.openPage("/login"));
    }

    @Test
    @DisplayName("Проверка успешной авторизации пользователя при валидных логине и пароле")
    @Story("Вход по логину и паролю")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Authorization")
    })
    public void userMustBeAutWithValidLoginAndPassword() {
        loginPage.setValueEmailInput("akytat@mailto.plus")
                .setValuePasswordInput(Credentials.config.getPassword())
                .clickSignInButton();

        step("Ожидаемый результат: открыта страница Projects", () -> {
            assertAll(() -> assertNotEquals("Projects!!!", projectPage.titleMustHaveText()),
                    () -> assertTrue(projectPage.titleMustHaveText().startsWith("Proj")),

                    () -> assertEquals("Projects", projectPage.titleMustHaveText())
            );
        });
    }

    @Story("Валидация полей ввода")
    @Severity(SeverityLevel.NORMAL)

    @CsvSource({
            ", 20091989Qwe!!!",
            "akytat@mailto.plus, ",
            ", "
    })
    @ParameterizedTest(name = "Проверка получения сообщения об ошибке при попытке авторизации пользователя " +
            "без ввода логина: {0}, пароля: {1} и с отсутствующими данными")
    void errorMessageShouldBeVisibleWithEnterInvalidData(String email, String password) {
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

        step("Ожидаемый результат: получено сообщение об ошибке", () ->
                assertEquals("This field is required", loginPage.getErrorMessage(),
                        "Текст сообщения не совпадает")
        );
    }

    @Story("Валидация полей ввода")
    @Severity(SeverityLevel.NORMAL)

    @CsvFileSource(resources = "/testData/loginTestData/incorrectDates.scv")
    @ParameterizedTest(name = "Проверка получения сообщения об ошибке при попытке авторизации пользователя " +
            "с помощью некорректного логина: {0} и пароля: {1}")
    void alertErrorMessageShouldBeVisibleWithEnterIncorrectData(String email, String password) {
        loginPage.setValueEmailInput(email)
                .setValuePasswordInput(password)
                .clickSignInButton();

        step("Ожидаемый результат: получено сообщение об ошибке", () ->
                loginPage.alertErrorMessage());
    }
}
