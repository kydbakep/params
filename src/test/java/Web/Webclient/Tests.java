package Web.Webclient;

import Web.Webclient.Pages.AuthPage;
import Web.Webclient.Pages.GooglePage;
import Web.Webclient.Pages.MainPage;
import Web.Webclient.Parameters.DefineParameters;
import com.codeborne.selenide.Configuration;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.TestClass;
import com.codeborne.selenide.junit.ScreenShooter;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by tober on 29.05.17.
 * Webclient tests
 */
public class Tests {

    private String status;

    @Before
    public void setBrowser() {

//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 1500;
        ScreenShooter.failedTests().captureSuccessfulTests = false;
    }

    @After
    public void stopActiveTest() throws InterruptedException {
        try {
            MainPage mainPage = new MainPage();
            mainPage.closeAllModals();
            logout();
        } catch (Throwable th) {
            System.out.println("Can't close Webclient session: is this NovaPoshta WebClient?\n");
        }
    }

    @Rule
    public final TestName testName = new TestName();

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests();

    @Rule
    public TestWatcher watcher = new TestWatcher() {

        @Override
        protected void succeeded(Description description) {
            status = "success";
        }

        @Override
        protected void failed(Throwable e, Description description) {
            status = "failed";
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            status = "skipped";
        }

        @Override
        protected void finished(Description description) {
            try {
                PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter("target/teststream.txt", true)));
                file.println("{\"method\":\"" + testName.getMethodName() + "\", \"status\":\"" + status + "\", " + "\"class\":\"" + getClass().getName()+"\"");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void logout() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.logOut();
    }

    @Test
    public void login() throws InterruptedException {
        AuthPage authPage = new AuthPage();
        MainPage mainPage = new MainPage();
        authPage.setUrl("http://webclient.sb.np.ua");
        authPage.setLogin("tober@i.ua");
        authPage.setPassword("boomkin.ua");
        authPage.loginToWebclient();
        mainPage.acceptInformMessage();
        mainPage.checkUserType();
    }

    @Test
    public void loginWithError() throws InterruptedException { // Не вірно обрано тип контрагента
        AuthPage authPage = new AuthPage();
        MainPage mainPage = new MainPage();
        authPage.setUrl("http://webclient.sb.np.ua");
        authPage.setLogin("test_tech01");
        authPage.setPassword("e10adc3949ba59abbe56e057f20f883e");
        authPage.loginToWebclient();
        mainPage.acceptInformMessage();
        mainPage.checkUserType();
    }

    @Test
    public void googleTest() throws InterruptedException {
        GooglePage googlePage = new GooglePage();
        googlePage.setUrl("https://www.google.com.ua");
        googlePage.find("злагода");
    }

    @Test
    public void googleTestWithParameters() throws InterruptedException, FileNotFoundException {
        DefineParameters defineParameters = new DefineParameters();
        GooglePage googlePage = new GooglePage();
        googlePage.setUrl(defineParameters.getParameter("google.url"));
        googlePage.find(defineParameters.getParameter("google.query"));
    }

    @Test
    public void smartTest() {

    }
}