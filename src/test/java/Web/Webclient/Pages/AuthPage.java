package Web.Webclient.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by tober on 29.05.17.
 * Webclient Autorization page.
 */
public class AuthPage {

    private String url;
    private String login ;
    private String password;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void loginToWebclient(){
        open(url);

        $(loginField).setValue(login);
        $(passwordField).setValue(password);

        $(loginButton).click();

        MainPage mainPage = new MainPage();
        $(mainPage.mainExitButton).shouldBe(Condition.visible);
        if ($(mainPage.mainExitButton).isDisplayed()) System.out.println("LogIn successful");
    }

    public void loginToWebclient(String as) throws InterruptedException {
        open(url);

        try {
            $(enterButton).shouldBe(Condition.visible);
        }catch (ElementNotFound notFound){
            Thread.sleep(1000);
            $(enterButton).shouldBe(Condition.visible);
        }

        if (as.equals("loyal")) {
            $(typeIsLoyal).click();
            $(loginField).setValue("tober@i.ua");
            $(passwordField).setValue("boomkin.ua");
        } else if (as.equals("corporate")){
            $(typeIsCorporate).click();
            $(loginField).setValue("test_tech01");
            $(passwordField).setValue("e10adc3949ba59abbe56e057f20f883e");
        }

        $(loginButton).click();

        MainPage mainPage = new MainPage();
        $(mainPage.mainExitButton).shouldBe(Condition.visible);
        if ($(mainPage.mainExitButton).isDisplayed()) System.out.println("LogIn successful");
    }

    public void startRegistration(){
        open(url);
        $(registerButton).click();
    }

    public void recoverPassword(){
        open(url);
        $(forgotButton).click();
    }

    //======================================================================================================================
    private static final WebElement loginField = $(By.xpath(".//input[@id='LoginForm_username']"));
    private static final WebElement passwordField = $(By.xpath(".//input[@id='LoginForm_password']"));
    private static final WebElement loginButton = $(By.xpath(".//input[@value='Увійти']"));
    WebElement enterButton = $(By.xpath(".//a[span='Вхід']"));
    private static final WebElement registerButton = $(By.xpath(".//a[.='Зареєструватись']"));
    private static final WebElement forgotButton = $(By.xpath(".//input[@value='Забули пароль?']"));

    private static final WebElement typeIsLoyal = $(By.xpath(".//input[@data-type='person']"));
    private static final WebElement typeIsCorporate = $(By.xpath(".//input[@data-type='business']"));
}
