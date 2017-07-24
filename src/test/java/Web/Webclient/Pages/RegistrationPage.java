package Web.Webclient.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 29.05.17.
 * Webclient Register page.
 */
public class RegistrationPage {
    private String city;
    private String lastname;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String sex;

    private int birthYear;
    private int birthMonth;
    private int birthDay;

    private String passportSeries;
    private String passportNumber;

    public void setCity(String city) {
        this.city = city;
        $(citySelectBoxArrow).click();
        $(By.xpath(".//li/a[.='" + city + "']")).shouldBe(Condition.visible).click();
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
        $(lastnameInput).setValue(lastName);
    }

    public void setFirstName(String firstName) {
        this.name = firstName;
        $(firstnameInput).setValue(firstName);
    }

    public void setMiddleName(String middleName) {
        this.surname = middleName;
        $(middlenameInput).setValue(middleName);
    }

    public void setPhone(String phone) {
        this.phone = phone;
        $(phoneInput).hover().setValue(phone);
    }

    public void setEmail(String email) {
        this.email = email;
        $(emailInput).setValue(email);
    }

    public void setSex(String sex) {
        try {
            this.sex = sex;
            $(registerButton).hover();
            $(selectGenderButton).click();
            if (sex.equals("m") || sex.equals("ч")) {
                $(genderIsMan).click();
            } else if (sex.equals("w") || sex.equals("ж")) {
                $(genderIsWoman).click();
            }
        }catch (ElementNotFound nf){
            System.out.println(nf.getMessage());
        }

    }

    public void setBirthday(int birthYear, int birthMonth, int birthDay) {
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;

        $(registrationCalendarButton).hover().click();

        while (true) {
            try {
                $(By.xpath(".//span[.='" + birthYear + "'][contains(@class,'year')]")).hover().click();
                break;
            } catch (Throwable throwable) {
                $(yearsBackButton).click();
            }
        }

        $(By.xpath(months+"["+birthMonth+"]")).click();
        $(By.xpath(days+"[.='"+birthDay+"']")).click();

    }

    public void setPassport(String passportSeries, String passportNumber) {
        try {
            this.passportSeries = passportSeries;
            this.passportNumber = passportNumber;
            $(passportseries).setValue(passportSeries);
            $(passportnumber).setValue(passportNumber);
        } catch (ElementNotFound nf){
            System.out.println(nf.getMessage());
        }

    }

    public void acceptTerms(){
        $(acceptTermsCheckbox).scrollTo().click();
    }

    public void pressRegisterButton(){
        $(registerButton).click();
    }


//======================================================================================================================

    private static final WebElement citySelectBoxArrow = $(By.xpath(".//span[@id='NovaposhtaRegistrationRequestForm_CityRefSelectBoxItArrowContainer']"));
    private static final WebElement lastnameInput = $(By.xpath(".//input[@id='NovaposhtaRegistrationRequestForm_LastName']"));
    private static final WebElement firstnameInput = $(By.xpath(".//input[@id='NovaposhtaRegistrationRequestForm_FirstName']"));
    private static final WebElement middlenameInput = $(By.xpath(".//input[@id='NovaposhtaRegistrationRequestForm_MiddleName']"));
    private static final WebElement phoneInput = $(By.xpath(".//input[@id='NovaposhtaRegistrationRequestForm_Phone']"));
    private static final WebElement emailInput = $(By.xpath(".//input[@id='NovaposhtaRegistrationRequestForm_Email']"));

    private static final WebElement selectGenderButton = $(By.xpath(".//i[@id='NovaposhtaRegistrationRequestForm_GenderSelectBoxItArrow']"));
    private static final WebElement genderIsMan = $(By.xpath(".//a[.='Чоловіча']"));
    private static final WebElement genderIsWoman = $(By.xpath(".//a[.='Жіноча']"));

    private static final WebElement registrationCalendarButton = $(By.xpath(".//span[@class='add-on btn btn-calendar-registration']"));
    private static final WebElement yearsBackButton = $(By.xpath(".//div[@class='datetimepicker-years']//i[@class='icon-arrow-left']"));
    private static final String months = "(.//div[@class='datetimepicker-months']//tbody//td/span)";
    private static final String days = "//div[@class='datetimepicker-days']//td[@class='day']";

    private static final WebElement passportseries = $(By.xpath(".//input[@id='NovaposhtaRegistrationRequestForm_PassportSeries']"));
    private static final WebElement passportnumber = $(By.xpath(".//input[@id='NovaposhtaRegistrationRequestForm_PassportNumber']"));

    private static final WebElement acceptTermsCheckbox = $(By.xpath(".//input[@id='NovaposhtaRegistrationRequestForm_Confirm']"));
    private static final WebElement termsInfo = $(By.xpath(".//a[@href='https://novaposhta.ua/uploads/misc/doc/conditions_program.pdf']"));
    private static final WebElement enterActivationCodeLink = $(By.xpath(".//a[@href='/registration/activation']"));
    private static final WebElement registerButton = $(By.xpath(".//input[@value='Зареєструватися']"));
}
