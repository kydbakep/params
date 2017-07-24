package Web.Webclient.Methods;

import Web.Webclient.Pages.CreateENPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 23.06.17.
 * Methods for working with counterParties
 */
public class CounterParties {

    public void counterPartySet() {
        $(selectCounterPartyButton).shouldBe(Condition.visible).click();
        $(counterPartyWindow).shouldBe(Condition.disappears);
    }

    public void setContactName(String contactPhone) {
        String phone = "(.//ul[@id='contacts_ul']/li[@data-phone='" + contactPhone + "'])[1]";
        WebElement ctPhone = $(By.xpath(phone));
        $(ctPhone).click();
        System.out.println("Обрано контактну особу: " + ctPhone.getText());
    }

    private void startCounterPartyCreation(String counterPartyType) throws InterruptedException {
        $(addNewCounterPartyButton).shouldBe(Condition.visible).click();
        $(organisationFrame).shouldBe(Condition.appears);
        if (counterPartyType.equals("organisation")) {
            try {
                $(typeisOrganisation).shouldBe(Condition.visible).click();
                $(ownershipFormlistButton).click();
            } catch (Throwable th) {
                Thread.sleep(2000);
                $(typeisOrganisation).shouldBe(Condition.visible).click();
                $(ownershipFormlistButton).click();
            }
        }
        if (counterPartyType.equals("person")) {

            try {
                $(typeIsPrivatePerson).shouldBe(Condition.visible).click();
            } catch (Throwable th) {
                Thread.sleep(2000);
                $(typeIsPrivatePerson).click();
            }
        }
    }

    public void createNewCounterparty(String lastname, String name, String phone) throws InterruptedException {
        startCounterPartyCreation("person");
        try {
            $(lastnameField).setValue(lastname);
            $(firstnameField).setValue(name);
            $(phoneNumberField).setValue(phone);
            $(saveCounterPartyButton).click();
            $(organisationFrame).shouldBe(Condition.disappears);
        } catch (Throwable th){
            $(organisationFrame).shouldBe(Condition.disappears);
        }
    }

    public void createNewCounterparty(String name, String lastname, String surname, String phone, String email) throws InterruptedException {
        startCounterPartyCreation("person");
        try {
            $(lastnameField).setValue(lastname);
            $(firstnameField).setValue(name);
            $(middleNameField).setValue(surname);
            $(phoneNumberField).setValue(phone);
            $(emailField).setValue(email);
            $(saveCounterPartyButton).click();
            $(organisationFrame).shouldBe(Condition.disappears);
        } catch (Throwable th){
            $(organisationFrame).shouldBe(Condition.disappears);
        }
    }

    public void createNewCounterparty(String ownershipForm, String name) throws InterruptedException {
        try {
            CreateENPage createENPage = new CreateENPage();
            createENPage.selectOrganisation(ownershipForm, name);
        } catch (Throwable throwable) {
            startCounterPartyCreation("organisation");
            try {
                String path = ".//*[@id='ownershipFormsListSelectBoxItOptions']//a[.='" + ownershipForm + "']";
                WebElement firm = $(By.xpath(path));
                $(firm).scrollTo().hover().click();
            } catch (Throwable th) {
                System.out.println(th.getMessage() + " Changing xpath");
                try {
                    String path = ".//span[@id='ownershipFormsListSelectBoxIt']/span[.='" + ownershipForm + "']";
                    WebElement firm = $(By.xpath(path));
                    $(firm).scrollTo().hover().click();
                } catch (ElementNotFound nf) {
                    System.out.println(nf.getMessage());
                    String path = ".//*[@id='ownershipFormsListSelectBoxItOptions']//a[.='" + ownershipForm + "']";
                    WebElement firm = $(By.xpath(path));
                    $(firm).scrollTo().hover().click();
                }
            }
            $(organisationNameField).setValue(name);
            $(saveCounterPartyButton).click();
            try {
                $(organisationFrame).shouldBe(Condition.disappears);
                System.out.println("Контору '" + ownershipForm + " " + name + "' створено.");
            } catch (Throwable th) {
                Thread.sleep(2000);
                $(organisationFrame).shouldBe(Condition.disappears);
                System.out.println("Контору '" + ownershipForm + " " + name + "' створено.");
            }
        }
    }

    public void editOrganisation(String ownershipForm, String name, String newOwnershipForm, String newName) throws InterruptedException {
        CreateENPage createENPage = new CreateENPage();
        WebElement formA = $(By.xpath(".//ul[@id='ownershipFormsListSelectBoxItOptions']/li/a[.='"+newOwnershipForm+"']"));
        WebElement formB = $(By.xpath(".//span[@id='ownershipFormsListSelectBoxItText'][.='"+newOwnershipForm+"']"));
        createENPage.selectOrganisation(ownershipForm,name);
        $(editOrganisationButton).click();
        $(organisationFrame).shouldBe(Condition.appears);
        $(ownershipFormlistButton).click();
        try {
            $(formA).scrollTo().click();
        }catch (Throwable th){
            $(formB).click();
        }
        $(organisationNameField).setValue(newName);
        $(saveCounterPartyButton).click();

        try {
            $(organisationFrame).shouldBe(Condition.disappears);
        } catch (Throwable th){
            Thread.sleep(1500);
            $(organisationFrame).shouldBe(Condition.disappears);
        }
        System.out.println("Контору '"+ownershipForm+" "+name+"' змінено на: '"+newOwnershipForm+" "+newName+"'.");
    }

    public void removeOrganisation(String ownershipForm, String name){
        CreateENPage createENPage = new CreateENPage();
        createENPage.selectOrganisation(ownershipForm,name);
        $(editOrganisationButton).click();
        $(organisationFrame).shouldBe(Condition.appears);
        $(counterpartyDeleteButton).shouldBe(Condition.visible).click();
        $(organisationFrame).shouldBe(Condition.disappears);
        System.out.println("Контору '"+ownershipForm+" "+name+"' видалено.");
    }

    public void selectPerson(String nameAndLastname) {
        String pathStart = "(.//ul[@id='contacts_ul']/li";
        String nameLastname = "[@data-description='" + nameAndLastname + "']";
        String pathEnd = ")[1]";
        WebElement person = $(By.xpath(pathStart + nameLastname + pathEnd));
        $(person).shouldBe(Condition.visible);
        System.out.println("Обрали контактну сосбу: "+person.getText());
        $(person).scrollTo().hover().click();
    }

    public void selectPerson(String name, String lastname) {
        String pathStart = "(.//ul[@id='contacts_ul']/li";
        String firstName = "[@data-firstname='" + name + "']";
        String lastName = "[@data-lastname='" + lastname + "']";
        String pathEnd = ")[1]";
        WebElement person = $(By.xpath(pathStart + firstName + lastName + pathEnd));
        $(person).shouldBe(Condition.visible);
        System.out.println(person.getText());
        $(person).scrollTo().hover().click();
    }

    public void selectPerson(String name, String lastname, String phone) {
        String pathStart = "(.//ul[@id='contacts_ul']/li";
        String firstName = "[@data-firstname='" + name + "']";
        String lastName = "[@data-lastname='" + lastname + "']";
        String contactPhone = "[@data-phone='" + phone + "']";
        String pathEnd = ")[1]";
        WebElement person = $(By.xpath(pathStart + firstName + lastName + contactPhone + pathEnd));
        $(person).shouldBe(Condition.visible);
        System.out.println(person.getText());
        $(person).scrollTo().hover().click();
    }

    public void editPerson(String lastname, String name, String phone){
        $(editContactButton).click();
        $(contactPersonFrame).shouldBe(Condition.appears);
        $(contactLastNameField).setValue(lastname);
        $(contactFirstNameField).setValue(name);
        $(contactPhoneField).setValue(phone);
        $(saveContactPersonButton).click();
        $(contactPersonFrame).shouldBe(Condition.disappears);
    }
    public void editPerson(String lastname, String name, String middleName, String phone, String email){
        $(editContactButton).click();
        $(contactPersonFrame).shouldBe(Condition.appears);
        $(contactLastNameField).setValue(lastname);
        $(contactFirstNameField).setValue(name);
        $(contactMiddleNameField).setValue(middleName);
        $(contactPhoneField).setValue(phone);
        $(contactEmailField).setValue(email);
        $(saveContactPersonButton).click();
        $(contactPersonFrame).shouldBe(Condition.disappears);
    }

    public void removePerson(String lastName, String firstName, String phone){
        selectPerson(firstName,lastName,phone);
        $(editContactButton).click();
        $(contactPersonFrame).shouldBe(Condition.appears);
        $(removeContactButton).click();
        $(contactPersonFrame).shouldBe(Condition.disappears);
    }

//    ==================================================================================================================

    private static final WebElement selectCounterPartyButton = $(By.xpath(".//button[@id='selectCounterpartyButton']"));
    private static final WebElement counterPartyWindow = $(By.xpath(".//div[@id='selectCounterpartyModal']"));

//  Создание/Редактирование контактного лица ===========================================================================

    private static final WebElement addNewCounterPartyButton = $(By.xpath(".//a[@class='btn btn-np btn-second createCounterparty']"));
    private static final WebElement editOrganisationButton = $(By.xpath(".//a[@id='editCounterparty']"));
    private static final WebElement typeIsPrivatePerson = $(By.xpath(".//ul[@id='tabsCounterpartyType']/li[@data-counterpartytype='PrivatePerson']"));
    private static final WebElement typeisOrganisation = $(By.xpath(".//ul[@id='tabsCounterpartyType']/li[@data-counterpartytype='Organization']"));
    private static final WebElement organisationFrame = $(By.xpath(".//div[@id='counterpartyModal']"));
    private static final WebElement saveCounterPartyButton = $(By.xpath(".//button[@id='counterpartySaveButton']"));
    private static final WebElement saveContactPersonButton = $(By.xpath(".//button[@id='counterpartyContactSaveButton']"));
    //    Елементи приватної особи
    private static final WebElement lastnameField = $(By.xpath(".//input[@id='counterpartyLastName']"));
    private static final WebElement firstnameField = $(By.xpath(".//input[@id='counterpartyFirstName']"));
    private static final WebElement middleNameField = $(By.xpath(".//input[@id='counterpartyMiddleName']"));
    private static final WebElement phoneNumberField = $(By.xpath(".//input[@id='counterpartyPhone']"));
    private static final WebElement emailField = $(By.xpath(".//input[@id='counterpartyEmail']"));
    //    Елементи юридичної особи особи
    private static final WebElement organisationNameField = $(By.xpath(".//input[@id='counterpartyOrganizationName']"));
    private static final WebElement ownershipFormlistButton = $(By.xpath(".//span[@id='ownershipFormsListSelectBoxItArrowContainer']"));
    private static final WebElement counterpartyDeleteButton = $(By.xpath(".//button[@id='counterpartyDeleteButton']"));
    //    Елементи контактної особи
    private static final WebElement editContactButton = $(By.xpath(".//a[@id='editContact']"));
    private static final WebElement removeContactButton = $(By.xpath(".//button[@id='counterpartyContactDeleteButton']"));
    private static final WebElement contactPersonFrame = $(By.xpath(".//div[@id='contactModal']"));
    private static final WebElement contactLastNameField = $(By.xpath("(.//input[@id='contactLastName'])[last()]"));
    private static final WebElement contactFirstNameField = $(By.xpath("(.//input[@id='contactFirstName'])[last()]"));
    private static final WebElement contactMiddleNameField = $(By.xpath("(.//input[@id='contactMiddleName'])[last()]"));
    private static final WebElement contactPhoneField = $(By.xpath("(.//input[@id='contactPhone'])[last()]"));
    private static final WebElement contactEmailField = $(By.xpath("(.//input[@id='contactEmail'])[last()]"));
}
