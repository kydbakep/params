package Web.Webclient;

import Web.Webclient.Pages.AuthPage;
import Web.Webclient.Pages.MainPage;
import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tober on 29.05.17.
 * Webclient tests
 */
public class Tests {

//    private CounterParties cp = new CounterParties();

    @Before
    public void setBrowser() {

        System.setProperty("webdriver.chrome.driver", "**/");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 1500;
    }

    @After
    public void stopActiveTest() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.closeAllModals();
        logout();
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
        authPage.setLogin("tober@i.ua");
        authPage.setPassword("boomkin.ua");
        authPage.loginToWebclient();
        mainPage.acceptInformMessage();
        mainPage.checkUserType();
    }
}