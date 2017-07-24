package Web.Webclient.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 29.05.17.
 * Webclient Main page.
 */
public class MainPage {

    public void acceptInformMessage() {
        try {
            System.out.println("User: " + userLoginLink.getText());
            $(informMessageFrame).shouldBe(Condition.appears);
            $(informMessageFrame).shouldBe(Condition.enabled);
            $(informMessageFrame).shouldBe(Condition.visible);
            $(informMessageFrameAcceptButton).followLink();
        } catch (Throwable th) {
            if (informMessageFrame.isDisplayed()) {
                System.out.println("User: " + userLoginLink.getText());
                $(informMessageFrame).shouldBe(Condition.appears).shouldBe(Condition.enabled).shouldBe(Condition.visible);
//                $(informMessageFrame).shouldBe(Condition.enabled);
//                $(informMessageFrame).shouldBe(Condition.visible);
                $(informMessageFrameAcceptButton).followLink();
            } else System.err.println("Хуйня якась!");
        }
    }

    public void closeAllModals() throws InterruptedException {
        WebElement activeModalCloser = $(By.xpath("(.//div[contains(@style,'display: block')][@aria-hidden='false']/div/button[@data-dismiss='modal'])[last()]"));

        try {
            while ($(activeModalCloser).exists()) {
                $(activeModalCloser).click();
            }
        } catch (Throwable th) {
            System.out.println("Modal closing Exception");
            Thread.sleep(1000);
            try {
                while ($(activeModalCloser).exists()) {
                    $(activeModalCloser).click();
                }
            } catch (Throwable thr) {
                System.out.println("No modals opened");
            }
        }
    }

    public void logOut() throws InterruptedException {
        AuthPage authPage = new AuthPage();
        try {
            $(mainExitButton).scrollTo().shouldBe(Condition.visible);
            $(mainExitButton).click();
            $(exitConfirmFrame).scrollTo().shouldBe(Condition.visible);
            try {
                $(exitConfirmButtonOK).click();
            } catch (Throwable throwable) {
                Thread.sleep(1000);
                $(exitConfirmButtonOK).click();
            }
            $(authPage.enterButton).scrollTo().shouldBe(Condition.visible);
            if ($(authPage.enterButton).isDisplayed()) System.out.println("LogOut successful");
        } catch (ElementNotFound nf) {
            $(userLoginButton).shouldBe(Condition.visible);
            System.out.println("Не можу логаутнись, тому що не залогінився ;)");
        }
    }

    public void exitDismiss() {
        try {
            $(mainExitButton).shouldBe(Condition.visible);
            $(mainExitButton).click();
            $(exitConfirmFrame).shouldBe(Condition.visible);
            $(exitConfirmButtonCancel).click();
            $(mainExitButton).shouldBe(Condition.visible);
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }

    public void checkUserType() {
        if ($(userIsLoyal).exists()) System.out.println("User type is: loyal");
        else if ($(userIsNoLoyal).exists()) System.out.println("User type is: corporate");
    }

    public void startCreateEN() {
        try {
            $(createENButton).shouldBe(Condition.visible).click();
        } catch (Throwable th) {
            if (informMessageFrame.isDisplayed()) {
                $(informMessageFrameAcceptButton).click();
                $(createENButton).shouldBe(Condition.visible).click();
            } else System.out.println("Iнформаційний фрейм відсутній");
            $(createENButton).shouldBe(Condition.visible).click();
        }
    }


    //======================================================================================================================
    WebElement mainExitButton = $(By.xpath(".//a[@id='exit']/span"));
    private static final WebElement exitConfirmButtonOK = $(By.xpath(".//div[@id='exitModal']//a"));
    private static final WebElement exitConfirmButtonCancel = $(By.xpath(".//div[@id='exitModal']//button[.='Відмінити']"));
    private static final WebElement exitConfirmFrame = $(By.xpath(".//div[@id='exitModal']"));
    private static final WebElement informMessageFrame = $(By.xpath("//div[@id='informMessagesModal']"));
    private static final WebElement informMessageFrameAcceptButton = $(By.xpath(".//*[@id='informMessagesModal']//button[.='Зрозуміло']"));
    private static final WebElement userLoginLink = $(By.xpath(".//div[@id='user_menu']/a[@class='reg']"));
    private static final WebElement userLoginButton = $(By.xpath(".//div[@id='user_menu']/a[@class='logo_in']"));
    private static final WebElement createENButton = $(By.xpath(".//a[@href='/newOrder/index']"));

    private static final WebElement userIsLoyal = $(By.xpath("//script[contains(text(),'IsLoyalty=true')]"));
    private static final WebElement userIsNoLoyal = $(By.xpath("//script[contains(text(),'IsLoyalty=false')]"));
    private static final WebElement loyalty = $(By.xpath("//script[contains(text(),'window.IsLoyalty')]"));
}
