package Web.Webclient.Pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by tober on 15.06.17.
 * google search test
 */

public class GooglePage {
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public void find(String string) throws InterruptedException {
        open(url);
        $(searchField).shouldBe(Condition.visible);
        $(searchField).sendKeys(string);

        $(searchField).pressEnter();
        $(resultList).shouldBe(Condition.appears).shouldBe(Condition.visible);
        System.out.println("\n***********************");
        System.out.println($(firstResult).getText());
        System.out.println("***********************\n");
    }

//======================================================================================================================

    private static final WebElement searchField = $(By.xpath(".//*[@id='lst-ib']"));
    private static final WebElement findGoogleButton = $(By.xpath(".//input[@name='btnK']"));
    private static final WebElement iWillBeHappyButton = $(By.xpath(".//input[@name='btnI']"));
    private static final WebElement resultList = $(By.xpath(".//div[@id='ires']"));
    private static final WebElement firstResult = $(By.xpath("(.//div[@id='ires']//div[@class='g'])[1]"));
}
