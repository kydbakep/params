package Web.Webclient.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 29.05.17.
 * Webclient Activation page.
 */
public class ActivationPage {

    private String info = $(infoFrame).getText();

    public String getInfo() {
        return info;
    }

//======================================================================================================================

    private static final WebElement infoFrame = $(By.xpath(".//div[@class='alert alert-block fade in']"));


}
