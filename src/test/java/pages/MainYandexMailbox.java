package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainYandexMailbox {
    WebDriver driver;

    // кнопка "Написать"
    private final By writeLetterButton = By.xpath("//span[contains(text(),'Написать')]");

    // поле "Кому"
    private final By destinationField = By.xpath("//div[@class='MultipleAddressesDesktop ComposeRecipients-MultipleAddressField ComposeRecipients-ToField tst-field-to']//div[@class='composeYabbles']");

    // поле "Тема"
    private final By topicField = By.xpath("//input[@class='composeTextField ComposeSubject-TextField']");

    // текст письма
    private final By textOfLetter = By.xpath("//div[@class='cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable cke_editable_themed cke_contents_ltr cke_htmlplaceholder']//div");

    // кнопка "Отправить"
    private final By sendLetterButton = By.xpath("//button[@class='Button2 Button2_pin_circle-circle Button2_view_default Button2_size_l']");

    // Входящие (слева)
    private final By inboxMails = By.xpath("//span[contains(text(),'Входящие')]");

    private final By reloadButton = By.xpath("//span[@title='Проверить, есть ли новые письма (Shift + o)']");

    private final By closeMailTabButton = By.xpath("//div[@class='ControlButton ControlButton_button_close']//button[@type='button']");

    private final By countOfEnterMail = By.xpath("//span[@class='mail-NestedList-Item-Info-Link-Text']");

    private final By moreMailButton = By.xpath("//button[@class=' nb-button _nb-large-pseudo-button mail-MessagesPager-button js-message-load-more']//span[@class='_nb-button-content']");

    private final By footer = By.xpath("//div[@class='mail-App-Footer mail-App-Footer_2pane']");


    public MainYandexMailbox(WebDriver driver) {
        this.driver = driver;
    }

    public void clickWriteLetterButton() {
        driver.findElement(writeLetterButton).click();
    }

    public void fillDestinationField() {
        driver.findElement(destinationField).clear();
        driver.findElement(destinationField).sendKeys("Sisofttest@yandex.ru");
    }

    public void fillTopicField() {
        driver.findElement(topicField).clear();
        driver.findElement(topicField).sendKeys("Simbirsoft theme");
    }

    public void fillTextOfLetter(int count) {
        driver.findElement(textOfLetter).sendKeys("Найдено " + count + " писем");
        }

    public void goToInboxMails() {
        driver.findElement(inboxMails).click();

    }

    public void clickSendLetterButton() {
        driver.findElement(sendLetterButton).click();
    }


    public void clickOnMoreMailButton() {
        boolean moreMailButtonState = driver.findElement(moreMailButton).isEnabled();
        if(moreMailButtonState) {
            while (driver.findElement(moreMailButton).isDisplayed()){
                WebDriverWait wait = new WebDriverWait(driver,10);
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(moreMailButton)));
                driver.findElement(moreMailButton).click();
            }
        }
    }

    // подсчет кол-ва писем
    public int getQuantityLetter() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        int mailList = driver.findElements(By.xpath("//span[contains(text(),'Sisofttest Тест')]")).size();
        boolean moreMailButtonState = driver.findElement(moreMailButton).isEnabled();
        if(moreMailButtonState) {
            while (driver.findElement(moreMailButton).isDisplayed()){
                driver.findElement(moreMailButton).click();
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//span[contains(text(),'Sisofttest Тест')]"), mailList));
            }
        }
        return driver.findElements(By.xpath("//span[contains(text(),'Sisofttest Тест')]")).size();
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
        driver.findElement(reloadButton).click();
    }

    public void clickReloadButtonAfterSend() {
        int countOfMail = Integer.parseInt(driver.findElement(countOfEnterMail).getText());
        int newCountMail = 0;
        while (countOfMail > newCountMail) {
            driver.findElement(reloadButton).click();
            int mail = (countOfMail + 1);
            String mails = Integer.toString(mail);
            WebElement mailss = new WebDriverWait(driver, 5000).until(driver -> driver.findElement(By.xpath(String.format("//a[@href='#tabs/relevant?extra_cond=only_new']//span[contains(text(),'%s')]",  mails))));
            newCountMail = Integer.parseInt(mailss.getText());
        }
    }

    public void closeMailTab() {
        driver.findElement(closeMailTabButton).click();
    }

}
