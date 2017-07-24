package Web.Webclient.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 29.05.17.
 * Webclient Activation page.
 */
public class PasswordRecoveryPage {

    public void getInfo(){
        System.out.println($(cardNumberForPasswordRecoveryFrame).getText());
    }

    public void clickForgotNumber(){
        $(forgotCardNumberButton).click();
    }

    public void clickRecoveryButton(){
        $(goToRecoveryButton).click();
    }

    public void getAlertInfo(){
        String text = $(errorSummary).getText();
        System.out.println("Alert: "+text);
    }

//======================================================================================================================

    private static final WebElement cardNumberForPasswordRecoveryFrame = $(By.xpath(".//*[@id='page']/form"));
    private static final WebElement forgotCardNumberButton = $(By.xpath(".//input[@onclick=\"location.href='/auth/forgotCard'\"][@type=\"button\"]"));
    private static final WebElement goToRecoveryButton = $(By.xpath(".//*[@type='submit'][@name='yt0']"));
    private static final WebElement errorSummary = $(By.xpath(".//div[@class='errorSummary']"));
}
