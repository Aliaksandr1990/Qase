package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private static final SelenideElement EMAIL_INPUT = $x("//input[@name='email']");
    private static final SelenideElement PASSWORD_INPUT = $x("//input[@name='password']");
    private static final SelenideElement SIGN_IN_BUTTON = $x("//button[.//span[text()='Sign in']]");
    private static final SelenideElement ERROR_MESSAGE = $x("//small[text()='This field is required']");

    public LoginPage openPage(){
        open("https://app.qase.io/login");
        return this;
    }

    public LoginPage setValueEmailInput(String email){
        EMAIL_INPUT.setValue(email);
        return this;
    }

    public LoginPage setValuePasswordInput(String password){
        PASSWORD_INPUT.setValue(password);
        return this;
    }

    public LoginPage clickSignInButton(){
        SIGN_IN_BUTTON.click();
        return this;
    }

    public String getErrorMessage(){
        String errorMessage = ERROR_MESSAGE.getText();
        return errorMessage;
    }
}
