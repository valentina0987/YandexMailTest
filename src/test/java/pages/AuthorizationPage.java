package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage {
    WebDriver driver;


    // поле логина
    @FindBy(xpath = "//input[@data-t='field:input-login']")
    private WebElement loginField;

    // поле пароля
    @FindBy(xpath = "//input[@data-t='field:input-passwd']")
    private WebElement passwordField;

    // вторая кнопка "Войти" после ввода пароля и логина
    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement enterAuthorizationButton;


    public AuthorizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void passAuthorization() {
        loginField.clear();
        loginField.sendKeys("Sisofttest");
        enterAuthorizationButton.click();
        passwordField.clear();
        passwordField.sendKeys("JMK-QD2-Lkg-PsE");
        enterAuthorizationButton.click();
    }
}

