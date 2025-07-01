package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.BrowserFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private WebDriver driver;
    private final static String LOGIN = "tomsmith";
    private final static String PASSWORD = "SuperSecretPassword!";
    private final static String WRONG_LOGIN = "111";
    private final static String WRONG_PASSWORD = "111";

    @BeforeAll
    public void setUp() {
        this.driver = BrowserFactory.getDriver("chrome");
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Проверка редиректа на страницу /secure после успешной авторизации")
    public void redirectAfterLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(LOGIN, PASSWORD);

        assertTrue(driver.getCurrentUrl().contains("secure"), "После заполнения формы авторизации не произошел редирект на страницу /secure");
    }

    @Test
    @Description("Проверка появления алерта с ошибкой при попытке авторизации с некорректными логином и паролем")
    public void errorAlertTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(WRONG_LOGIN, WRONG_PASSWORD);

        boolean isErrorAlertVisible = loginPage.isErrorAlertVisible(driver);

        assertTrue(isErrorAlertVisible, "После ввода некорректных логина и пароля не появился алерт с ошибкой");
    }

}
