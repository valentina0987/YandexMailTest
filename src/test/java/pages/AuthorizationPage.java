package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthorizationPage {
    WebDriver driver;

    // поле логина
    private final By loginField = By.xpath("//input[@data-t='field:input-login']");

    // поле пароля
    private final By passwordField = By.xpath("//input[@data-t='field:input-passwd']");

    // вторая кнопка "Войти" после ввода пароля и логина
    private final By enterAuthorizationButton = By.xpath("//button[@id='passp:sign-in']");


    public AuthorizationPage(WebDriver driver) { this.driver = driver; }

    public void fillLoginField() {
        driver.findElement(loginField).clear();
        driver.findElement(loginField).sendKeys("Sisofttest");
    }

    public void fillPasswordField() {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys("JMK-QD2-Lkg-PsE");
    }

    public void clickEnterAuthorizationButton() {
        driver.findElement(enterAuthorizationButton).click();
    }
}

