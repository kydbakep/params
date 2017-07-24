package Web.Webclient;

import Web.Webclient.Pages.GooglePage;
import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tober on 15.06.17.
 *
 */
public class GoogleTest {
    @Before
    public void setBrowser() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 1500;

    }

    @Test
    public void runTest() throws InterruptedException {
        GooglePage googlePage = new GooglePage();
        googlePage.setUrl("https://www.google.com.ua");
        googlePage.find("радість");
    }

}
