package Web.API_2_0.Classes;

import Web.Webclient.Pages.AuthPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 14.07.17.
 * Генерація API ключа через особистий кабінет
 */
public class Generator {
    public Generator() {
    }

    public Generator(String userType) {
    }

    private static AuthPage webClient = new AuthPage();
    private static Properties property = new Properties();

    public static void main(String[] args) {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 1500;

        try {
            FileInputStream inputStream = new FileInputStream("./src/main/resources/config.properties");
            property.load(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String host = property.getProperty("webClient.host");
        String login = property.getProperty("webClient.loyalUserName");
        String password = property.getProperty("webClient.loyalUserPassword");
        webClient.setUrl(host);
        webClient.setLogin(login);
        webClient.setPassword(password);
        webClient.loginToWebclient();

        WebElement settingsLink = $(By.xpath("(.//a[@href='/settings/index'])[last()]"));
        $(settingsLink).click();
        WebElement settingsFrame = $(By.xpath(".//div[@class='tabbable tabs-left shadowed_box']"));
        $(settingsFrame).shouldBe(Condition.appears);
        WebElement api2_0_link = $(By.xpath(".//a[.='API 2.0']"));
        $(api2_0_link).click();

        System.out.println(getApiKey());
    }

    private static String getApiKey() {
        try {
            WebElement generatedKey = $(By.xpath("(.//td[@class='apiKey'])[last()]"));
            $(generatedKey).shouldBe(Condition.appears).scrollTo();
            return $(generatedKey).getText();
        } catch (Throwable th) {
            System.out.print("Немає згенерованих ключів! Генеруюємо...");
            WebElement generateKeyButton = $(By.xpath(".//a[@id='generateAPIKey']"));
            $(generateKeyButton).shouldBe(Condition.appears).click();
            WebElement generatedKey = $(By.xpath("(.//td[@class='apiKey'])[last()]"));
            $(generatedKey).shouldBe(Condition.appears).scrollTo();
            return $(generatedKey).getText();
        }
    }

    public String getKey(String UserType) throws IOException, ParseException, JSONException {
        RequestSender sender = new RequestSender();
        sender.sendRequest(UserType,"generateApiKey.json");
        ResponseParser parser = new ResponseParser();
        return parser.getKey("data", "ApiKey");
    }
}
