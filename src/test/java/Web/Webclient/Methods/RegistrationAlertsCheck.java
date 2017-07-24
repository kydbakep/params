package Web.Webclient.Methods;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 30.05.17.
 * Registration alerts
 */
public class RegistrationAlertsCheck {

    public void checkAllAlerts() {
        checkForNoNameAlert();
        checkForNoMiddleNameAlert();
        checkForNoLastNameAlert();
        checkForNoCityAlert();
        checkForNoPhoneAlert();
        checkForNoEmailAlert();
        checkForNoGenderAlert();
        checkForNoTermsAcceptedAlert();
    }

    public void checkForNoNameAlert() {
        try {
            $(noNameAlert).shouldBe(Condition.visible);
            System.out.println(noNameAlert.getText());
        } catch (Throwable throwable) {
            System.out.println("Поле \"Ім'я\" заповнено вірно");
        }
    }

    public void checkForNoMiddleNameAlert() {
        try {
            $(noMiddleNameAlert).shouldBe(Condition.visible);
            System.out.println(noMiddleNameAlert.getText());
        } catch (Throwable throwable) {
            System.out.println("Поле \"По-батькові\" заповнено вірно");
        }
    }

    public void checkForNoLastNameAlert() {
        try {
            $(noLastNameAlert).shouldBe(Condition.visible);
            System.out.println(noLastNameAlert.getText());
        } catch (Throwable throwable) {
            System.out.println("Поле \"Прізвище\" заповнено вірно");
        }
    }

    public void checkForNoCityAlert() {
        try {
            $(noCityAlert).shouldBe(Condition.visible);
            System.out.println(noCityAlert.getText());
        } catch (Throwable throwable) {
            System.out.println("Поле \"Місто\" заповнено вірно");
        }
    }

    public void checkForNoPhoneAlert() {
        try {
            $(noPhoneAlert).shouldBe(Condition.visible);
            System.out.println(noPhoneAlert.getText());
        } catch (Throwable throwable) {
            System.out.println("Поле \"Телефон\" заповнено вірно");
        }
    }

    public void checkForNoEmailAlert() {
        try {
            $(noEmailAlert).shouldBe(Condition.visible);
            System.out.println(noEmailAlert.getText());
        } catch (Throwable throwable) {
            System.out.println("Поле \"E-mail\" заповнено вірно");
        }
    }

    public void checkForNoGenderAlert() {
        try {
            $(noGenderAlert).shouldBe(Condition.visible);
            System.out.println(noGenderAlert.getText());
        } catch (Throwable throwable) {
            System.out.println("Поле \"Стать\" заповнено вірно");
        }
    }

    public void checkForNoTermsAcceptedAlert() {
        try{
        $(termsIsNotAcceptedAlert).shouldBe(Condition.visible);
            System.out.println(termsIsNotAcceptedAlert.getText());}
        catch (Throwable throwable){
            System.out.println("Згоду на умови Програми \"Збільшуй можливості\" підтверджено");
        }
    }


//======================================================================================================================

    private static final String alert = "Не вказано або вказано невірно значення ";
    private static final WebElement noNameAlert = $(By.xpath(".//div[@class='errorSummary']//li[.=\"" + alert + "Ім'я\"]"));
    private static final WebElement noMiddleNameAlert = $(By.xpath(".//div[@class='errorSummary']//li[.=\"" + alert + "По-батькові\"]"));
    private static final WebElement noLastNameAlert = $(By.xpath(".//div[@class='errorSummary']//li[.=\"" + alert + "Прізвище\"]"));
    private static final WebElement noCityAlert = $(By.xpath(".//div[@class='errorSummary']//li[.=\"" + alert + "Місто\"]"));
    private static final WebElement noPhoneAlert = $(By.xpath(".//div[@class='errorSummary']//li[.=\"" + alert + "Телефон\"]"));
    private static final WebElement noEmailAlert = $(By.xpath(".//div[@class='errorSummary']//li[.=\"" + alert + "E-mail\"]"));
    private static final WebElement noGenderAlert = $(By.xpath(".//div[@class='errorSummary']//li[.=\"" + alert + "Стать\"]"));

    private static final WebElement termsIsNotAcceptedAlert = $(By.xpath(".//div[@class='errorSummary']//li[.='Необхідно підтвердити свою згоду на умови Програми \"Збільшуй можливості\"']"));
}
