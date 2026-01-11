package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignInTest extends BaseTest{

    @Test
    public void userMustEnterValidLoginAndPassword(){
        loginPage.openPage()
                .setValueEmailInput("akytat@mailto.plus")
                .setValuePasswordInput("20091989Qwe!!!")
                .clickSignInButton();

        assertEquals("Projects",projectPage.titleMustHaveText());
    }

    @Test
    public void enterOnlyPassword(){
        loginPage.openPage()
                .setValuePasswordInput("20091989Qwe!!!")
                .clickSignInButton();

        assertEquals("This field is required",loginPage.getErrorMessage(),
                "Текст сообщения не совпадает");
    }

    @Test
    public void enterOnlyEmail(){
        loginPage.openPage()
                .setValueEmailInput("akytat@mailto.plus")
                .clickSignInButton();

        assertEquals("This field is required",loginPage.getErrorMessage(),
                "Текст сообщения не совпадает");
    }

    @Test
    public void emptyFields(){
        loginPage.openPage()
                .clickSignInButton();

        assertEquals("This field is required",loginPage.getErrorMessage(),
                "Текст сообщения не совпадает");
    }
}
