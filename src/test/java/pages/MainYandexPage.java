package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainYandexPage {
    WebDriver driver;



    // поле поиска Яндекс (главная страница"
    @FindBy(xpath = "//input[@id='text']")
    private WebElement searchField;

    // кнопка "Войти"
    @FindBy(xpath = "//div[contains(text(),'Войти')]")
    private WebElement enterButton;

    // кнопка "Почта"
    @FindBy(xpath = "//div[@class='desk-notif-card__mail-title']")
    private WebElement mailboxButton;


    public MainYandexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void enterButtonClick() {
        enterButton.click();
    }

    public void goToMailbox() {
        mailboxButton.click();
    }
}

