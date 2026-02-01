package tests.ui;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.BaseTest;

public class NavigationBarTest extends BaseTest {

    @CsvSource({
            "Projects",
            "Workspace",
            "Dashboards",
            "Queries",
            "Apps"
    })
    @ParameterizedTest(name = "Элементы навигационного меню должны быть видимыми")
    void search(String nameElement) {

    }
}
