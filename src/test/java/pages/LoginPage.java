package pages;

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

    public void Login(String login, String password) {
        // Ожидание формы авторизации
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(loginInput));

        // Заполнение формы авторизации
        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();

        // Ожидание редиректа
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("secure"));
        } catch (TimeoutException e) {}

    }

    public boolean isErrorAlertVisible(WebDriver driver) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(errorAlert));

            boolean isErrorAlertVisible = driver.findElement(errorAlert).isDisplayed();

            return isErrorAlertVisible;
        } catch (TimeoutException e) {
            boolean isErrorAlertVisible = false;

            return isErrorAlertVisible;
        }

    }

}
