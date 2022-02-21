package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainYandexPage {
    WebDriver driver;

    // поле поиска Яндекс (главная страница"
    private final By searchField = By.xpath("//input[@id='text']");

    // кнопка "Войти"
    private final By enterButton = By.xpath("//div[contains(text(),'Войти')]");
    // кнопка "Почта"
    private final By mailboxButton = By.xpath("//div[@class='desk-notif-card__mail-title']");

    // кнопка закрытия баннера "Сделать стартовой страницей"
    private final By popupClose = By.xpath("//div[@class='popup-close-btn']");


    public MainYandexPage(WebDriver driver) {
        this.driver = driver;
    }

    public void closePopupBanner() {
        if(driver.findElement(popupClose).isEnabled()) {
            driver.findElement(popupClose).click();
        }
    }

    public void enterButtonClick() {
        driver.findElement(enterButton).click();
    }

    public void goToMailbox() {
        driver.findElement(mailboxButton).click();
    }
}

