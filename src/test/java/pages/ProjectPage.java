package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {

    private static final SelenideElement PROJECT_PAGE_TITLE = $x("//h1[text()='Projects']");

    public String titleMustHaveText(){
        return PROJECT_PAGE_TITLE.getText();
    }
}
