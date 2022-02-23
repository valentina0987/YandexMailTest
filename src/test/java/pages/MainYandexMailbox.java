package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class MainYandexMailbox {
    WebDriver driver;


    // кнопка "Написать"
    @FindBy(xpath = "//span[@class='mail-ComposeButton-Text']")
    private WebElement writeLetterButton;


    // поле "Кому"
    @FindBy(xpath = "//div[contains(@class, 'tst-field-to')]/descendant::div[@class='composeYabbles']")
    private WebElement destinationField;


    // поле "Тема"
    @FindBy(xpath = "//input[@class='composeTextField ComposeSubject-TextField']")
    private WebElement topicField;


    // текст письма
    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement textOfLetter;


    // кнопка "Отправить"  ПОМЕНЯТЬ ЛОКАТОР
    @FindBy(xpath = "//button[@aria-disabled='false']")
    private WebElement sendLetterButton;


    // Входящие (слева)
    @FindBy(xpath = "//*[contains(@class,'estedList-Item_current')]")
    private WebElement inboxMails;


    @FindBy(xpath = "//span[@class='mail-ComposeButton-Refresh js-main-action-refresh ns-action']")
    private WebElement reloadButton;

    @FindBy(xpath = "//div[@class='ControlButton ControlButton_button_close']//button[@type='button']")
    private WebElement closeMailTabButton;


    @FindBy(xpath = "//span[@class='mail-NestedList-Item-Info-Link-Text']")
    private WebElement countOfEnterMail;

    @FindBy(xpath = "//span[contains(text(),'Sisofttest Тест')]")
    private List<WebElement> letterTheme;

    @FindBy(xpath = "//button[contains(@class, ' nb-button')]")
    private WebElement moreMailButton;


    public MainYandexMailbox(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void WriteNewLetter(int count) {
        writeLetterButton.click();
        destinationField.sendKeys("Sisofttest@yandex.ru");
        topicField.clear();
        topicField.sendKeys("Simbirsoft theme");
        textOfLetter.sendKeys("Найдено " + count + " писем");
        sendLetterButton.click();
    }


    public void goToInboxMails() {
        inboxMails.click();

    }


    public void clickOnMoreMailButton() {
        boolean moreMailButtonState = moreMailButton.isEnabled();
        if(moreMailButtonState) {
            while (moreMailButton.isDisplayed()){
                WebDriverWait wait = new WebDriverWait(driver,10);
                wait.until(ExpectedConditions.visibilityOf(moreMailButton));
                moreMailButton.click();
            }
        }
    }

    // подсчет кол-ва писем
    public int getQuantityLetter() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        int mailList = letterTheme.size();
        boolean moreMailButtonState = moreMailButton.isEnabled();
        if(moreMailButtonState) {
            while (moreMailButton.isDisplayed()){
                moreMailButton.click();
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//span[contains(text(),'Sisofttest Тест')]"), mailList));
            }
        }
        return letterTheme.size();
    }


    public void switchBrowserTab() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void clickReloadButton() {
        reloadButton.click();
    }

    public void clickReloadButtonUntilNewLetterAppears() {
        int countOfMail = Integer.parseInt(countOfEnterMail.getText());
        int newCountMail = 0;
        while (countOfMail > newCountMail) {
            reloadButton.click();
            newCountMail = Integer.parseInt(countOfEnterMail.getText());
        }
    }

    public void closeMailTab() {
        closeMailTabButton.click();
    }

}
