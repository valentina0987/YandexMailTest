package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainYandexMailbox;
import pages.MainYandexPage;


public class SendLettersAndCountNumberTest extends TestBase {

    @Test
    public void test() throws InterruptedException {
        MainYandexPage mainYandexPage  = new MainYandexPage(driver);
        mainYandexPage.enterButtonClick();
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        authorizationPage.passAuthorization();
        mainYandexPage.goToMailbox();
        MainYandexMailbox mainYandexMailbox = new MainYandexMailbox(driver);
        mainYandexMailbox.switchBrowserTab();
        mainYandexMailbox.goToInboxMails();
        int countOfMails = mainYandexMailbox.getQuantityLetter();
        mainYandexMailbox.WriteNewLetter(countOfMails);
        mainYandexMailbox.goToInboxMails();
        mainYandexMailbox.clickReloadButtonUntilNewLetterAppears();
        mainYandexMailbox.clickOnMoreMailButton();
        int actualResult = mainYandexMailbox.getQuantityLetter();
        int expectedResult = countOfMails + 1;

        Assert.assertEquals(actualResult, expectedResult, "Число писем не увеличилось на одно");


    }

}
