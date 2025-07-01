package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.BrowserFactory;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(AllureJunit5.class)
public class LoginTest {

    private WebDriver driver;
    private final static String LOGIN = "tomsmith";
    private final static String PASSWORD = "SuperSecretPassword!";

    @BeforeEach
    public void setUp() {
        this.driver = BrowserFactory.getDriver("chrome");
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    public static Stream<Arguments> loginDataProvider() {
        return Stream.of(
                /*Arguments.of(LOGIN, PASSWORD, true),
                Arguments.of(LOGIN, "qwe", false),
                Arguments.of("qwe", PASSWORD, false),
                Arguments.of("qwe", "qwe", false),
                Arguments.of("qwe", "", false),
                Arguments.of("", "qwe", false),
                Arguments.of("", "", false),
                Arguments.of("0", "", false),
                Arguments.of("", "0", false),
                Arguments.of("0", "0", false),
                Arguments.of(" qwe", "qwe", false),
                Arguments.of("qwe ", "qwe", false),
                Arguments.of("1qwe", "qwe", false),
                Arguments.of("qwe1", "qwe", false),*/
                Arguments.of(LOGIN, "!@@#!$#@$&*", false)
        );
    }

    @ParameterizedTest
    @MethodSource("loginDataProvider")
    @DisplayName("Редирект после авторизации")
    @Description("Проверка редиректа на страницу /secure после авторизации")
    @Severity(SeverityLevel.CRITICAL)
    public void redirectAfterLoginTest(String login, String password, boolean isSuccessAuthorizationExpected) {
        // Заполнение формы авторизации
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, password);

        // Ожидание редиректа
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains("secure"));
        } catch (TimeoutException e) {
            System.out.println("Редирект на страницу /secure не произошел");
        }

        if (isSuccessAuthorizationExpected) {
            assertTrue(driver.getCurrentUrl().contains("secure"), "Для данных: " + login + "/" + password + " ожидался редирект на страницу /secure");
        } else {
            assertFalse(driver.getCurrentUrl().contains("secure"), "Для данных: " + login + "/" + password + " не ожидался редирект на страницу /secure");
        }
    }

    @ParameterizedTest
    @MethodSource("loginDataProvider")
    @DisplayName("Алерт с ошибкой авторизации")
    @Description("Проверка появления алерта с ошибкой при попытке авторизации с некорректными логином и паролем")
    @Severity(SeverityLevel.NORMAL)
    public void errorAlertTest(String login, String password, boolean isSuccessAuthorizationExpected) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, password);

        boolean isErrorAlertVisible = loginPage.isErrorAlertVisible();

        if (isSuccessAuthorizationExpected) {
            assertFalse(isErrorAlertVisible, "Для данных: " + login + "/" + password + " не ожидался алерт с ошибкой");
        } else {
            assertTrue(isErrorAlertVisible, "Для данных: " + login + "/" + password + " ожидался алерт с ошибкой");
        }
    }

}
