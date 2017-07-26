package Web.Webclient;

import Web.Webclient.Methods.CounterParties;
import Web.Webclient.Pages.AuthPage;
import Web.Webclient.Pages.GooglePage;
import Web.Webclient.Pages.MainPage;
import Web.Webclient.Parameters.DefineParameters;
import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by tober on 29.05.17.
 * Webclient tests
 */
public class Tests {

    private CounterParties cp = new CounterParties();

    @Before
    public void setBrowser() {

//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 1500;

    }

    @After
    public void stopActiveTest() throws InterruptedException {
        try {
            MainPage mainPage = new MainPage();
            mainPage.closeAllModals();
            logout();
        } catch (Throwable th){
            System.out.println("Can't close Webclient session: is this NovaPoshta WebClient?\n");
        }

    }

    private void logout() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.logOut();
    }

    @Test
    public void login() {
        AuthPage authPage = new AuthPage();
        MainPage mainPage = new MainPage();
        authPage.setUrl("http://webclient.sb.np.ua");
        System.out.println("Entering login");
        authPage.setLogin("tober@i.ua");
        System.out.println("Entering password");
        authPage.setPassword("boomkin.ua");
        System.out.println("Click on logIn button");
        authPage.loginToWebclient();
        System.out.println("Accepting warning");
        mainPage.acceptInformMessage();
        System.out.println("Checking user type");
        mainPage.checkUserType();
    }

    @Test
    public void googleTest() throws InterruptedException {
        GooglePage googlePage = new GooglePage();
        googlePage.setUrl("https://www.google.com.ua");
        googlePage.find("москаль");
    }

    @Test
    public void googleTestWithParameters() throws InterruptedException, FileNotFoundException {
        DefineParameters defineParameters = new DefineParameters();
        GooglePage googlePage = new GooglePage();
        googlePage.setUrl(defineParameters.getParameter("google.url"));
        googlePage.find(defineParameters.getParameter("google.query"));
    }

}