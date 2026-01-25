package pages.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class NavigationBar {

    private static final SelenideElement PROJECT_TITLE = $x("//nav[@aria-label='Main navigation']//a[@href='/projects']");
    private static final SelenideElement WORKSPACE_TITLE = $x("//nav[@aria-label='Main navigation']//a[@href=/'workspace']");
    private static final SelenideElement DASHBOARD_TITLE = $x("//nav[@aria-label='Main navigation']//a[@href='/report/dashboards']");
    private static final SelenideElement QUERIES_TITLE = $x("//nav[@aria-label='Main navigation']//a[@href='/report/queries-list']");
    private static final SelenideElement APPS_TITLE = $x("//nav[@aria-label='Main navigation']//a[@href='/apps']");

    public String test() {
        return PROJECT_TITLE.shouldBe(visible).getText();
    }

    public String test1() {
        return WORKSPACE_TITLE.shouldBe(visible).getText();
    }

    public String test2() {
        return DASHBOARD_TITLE.shouldBe(visible).getText();
    }

    public String test3() {
        return QUERIES_TITLE.shouldBe(visible).getText();
    }

    public String test4() {
        return APPS_TITLE.shouldBe(visible).getText();
    }
}
