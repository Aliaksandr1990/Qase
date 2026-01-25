package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageElements.Input.fillInputWithData;

public class LoginPage extends BasePage {

    private static final SelenideElement SIGN_IN_BUTTON = $("button[type='submit']");
    private static final SelenideElement ERROR_MESSAGE = $x("//small[text()='This field is required']");
    private static final SelenideElement ALERT_ERROR_MESSAGE = $x("//div[@role='alert']/span");

    @Step("Ввести валидный логин {0}")
    public LoginPage setValueEmailInput(String email) {
        fillInputWithData("Work email", email);
        return this;
    }

    @Step("Ввести валидный пароль {0}")
    public LoginPage setValuePasswordInput(String password) {
        fillInputWithData("Password", password);
        return this;
    }

    @Step("Нажать на кнопку Sign In")
    public LoginPage clickSignInButton() {
        SIGN_IN_BUTTON.click();
        return this;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE.getText();
    }

    public LoginPage alertErrorMessage() {
        ALERT_ERROR_MESSAGE.shouldBe(visible);
        return this;
    }
}
