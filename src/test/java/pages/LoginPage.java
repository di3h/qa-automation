package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private By loginInput = By.id("username");
    private By passwordInput = By.id("password");
    private By submitButton = By.xpath(".//button[@type='submit']");
    private By errorAlert = By.xpath(".//div[@class='flash error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение и отправка формы авторизации")
    public void login(String login, String password) {
        // Ожидание формы авторизации
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(loginInput));

        // Заполнение формы авторизации
        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    @Step("Ожидание и проверка отображения алерта с ошибкой")
    public boolean isErrorAlertVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(errorAlert));

            boolean isErrorAlertVisible = driver.findElement(errorAlert).isDisplayed();

            return isErrorAlertVisible;
        } catch (TimeoutException e) {
            return false;
        }

    }

}
