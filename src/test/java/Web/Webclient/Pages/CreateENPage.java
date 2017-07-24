package Web.Webclient.Pages;

import Web.Webclient.Methods.CounterParties;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 31.05.17.
 * Create EN page
 */
public class CreateENPage {

    //    private String warehouse;
    private String whouse;
    private Integer EDRPOU = 0;

    public void changeSender(String city, int warehouse, String contactPhone) throws InterruptedException {
        CounterParties cp = new CounterParties();
        clickChangeSenderButton();
        setCity(city);
        setWarehouse(warehouse);
        cp.setContactName(contactPhone);
        cp.counterPartySet();
    }

    public void clickChangeSenderButton() {
        try {
            System.out.println("********************");
            System.out.println("Обираємо відправника");
            $(setSenderButton).shouldBe(Condition.visible).click();
            $(counterPartyWindow).shouldBe(Condition.visible);
        } catch (Throwable th) {
            if (setSenderButton.isDisplayed()) {
                $(setSenderButton).shouldBe(Condition.visible).click();
                $(counterPartyWindow).shouldBe(Condition.visible);
            } else System.err.println("Де бля кнопка зміни відправника?");
        }
    }

    public void clickChangeRecipientButton() {
        $(setRecipientButton).shouldBe(Condition.visible).click();
        $(counterPartyWindow).shouldBe(Condition.visible);
    }

    public void changeRecipient(String city, int warehouse, String contactPhone) throws InterruptedException {
        try {
            System.out.println("*******************");
            System.out.println("Обираємо отримувача");
            $(setRecipientButton).shouldBe(Condition.visible).click();
        } catch (Throwable th) {
            if (setRecipientButton.isDisplayed()) {
                $(setRecipientButton).shouldBe(Condition.visible).click();
            } else System.err.println("Де бля кнопка зміни отримувача?");
        }
        setCity(city);
        setWarehouse(warehouse);
        CounterParties cp = new CounterParties();
        cp.setContactName(contactPhone);
        cp.counterPartySet();
    }

    private void checkForRefreshed(WebElement element) {
        try {
            $(element).shouldBe(Condition.disappears);
            $(element).shouldBe(Condition.appears);
        } catch (Throwable th) {
            $(element).shouldBe(Condition.visible);
        }
    }

    private void setCity(String city) throws InterruptedException {
        try {
            $(cityInputCleaner).shouldBe(Condition.visible).hover().click();
            checkForRefreshed(citiesList);
        } catch (Throwable th) {
            System.out.println("поле Місто не очищено");
        }

        $(cityInput).sendKeys(city);
        checkForRefreshed(citiesList);

        String cityStr = ".//*[@id='cities_ul']//a[normalize-space(text()) = normalize-space('" + city + "')]";
        WebElement cityEl = $(By.xpath(cityStr));
        $(cityEl).shouldBe(Condition.visible).click();
        System.out.println("Обрано місто: " + city);
    }
    public void selectCity(String cityName){
        try {
            WebElement city = $(By.xpath(".//*[@id='cities_ul']//a[normalize-space(text()) = normalize-space('" + cityName + "')]"));
            checkForRefreshed(citiesList);
            $(city).shouldBe(Condition.visible).scrollTo().click();
        } catch (Throwable th){
            System.out.println(th.getMessage());
            WebElement city = $(By.xpath(".//*[@id='cities_ul']//a[normalize-space(text()) = normalize-space('" + cityName + "')]"));
            $(city).shouldBe(Condition.visible).scrollTo().click();
        }
    }

    private void setWarehouse(int warehouse) {
        try {
            $(addressList).shouldBe(Condition.visible);
        } catch (Throwable th) {
            System.out.println("Чомусь довго не з'являвся список");
            $(addressList).shouldBe(Condition.visible);
        }

        $(warehouseInput).sendKeys(String.valueOf(warehouse));
        checkForRefreshed(addressList);

        whouse = ".//li[@data-warehouse='true']" +
                "[contains(@data-description,'№" + warehouse + ":') or " + //Двокрапка після номера відділення
                " contains(@data-description,'№ " + warehouse + " ') or " + //Номер відділення в пробілах
                " contains(@data-description,'№" + warehouse + " ')]"; //Пробіл після номера відділення

        WebElement warehouseNumber = $(By.xpath(whouse));
        $(warehouseNumber).click();
        System.out.println("Обрано " + $(warehouseNumber).getText());
    }

    public void getAllWareHouses() {
        SelenideElement el = (SelenideElement) elList;
        List<WebElement> wh = el.findElements(By.xpath(warehouses));

        System.out.println(this.whouse);
        for (WebElement warehouses : wh) {
            System.out.println(warehouses.getText());
        }
    }

    public void counterPartyWindowClose() {
        $(counterPartyWindowClose).click();
        $(counterPartyWindow).shouldBe(Condition.disappears);
    }

//======================================================================================================================

    public void setPayer(String payerType, Integer EDRPOU) throws InterruptedException {

        WebElement thirdPartyPayer = $(By.xpath(".//ul[@id='counterparties_ul_counterparty']/li[@id][@data-edrpou='" + EDRPOU + "']"));

        if (payerType.equals("sender") || payerType.equals("відправник")) {
            $(setPayerSender).shouldBe(Condition.visible).click();
        }
        if (payerType.equals("recipient") || payerType.equals("отримувач")) {
            $(setPayerRecipient).shouldBe(Condition.visible).click();
        }
        if (payerType.equals("third person") || payerType.equals("третя особа")) {
            $(setPayerThirdPerson).shouldBe(Condition.visible).click();
            $(thirdPersonChangeButton).scrollTo().shouldBe(Condition.visible).click();
            $(counterPartyListFrame).shouldBe(Condition.appears);

            if ($(noWritesFoundMessage).isDisplayed()) {
                createThirdPartyPayer(EDRPOU);
            } else {
                System.out.println("Обираємо платника (Третя особа): " + thirdPartyPayer.getText());
                $(thirdPartyPayer).click();
                $(confirmChooseButton).click();
            }
        }
    }

    private void createThirdPartyPayer(Integer EDRPOU) throws InterruptedException {

        WebElement thirdPartyPayer = $(By.xpath(".//ul[@id='counterparties_ul_counterparty']/li[@id][@data-edrpou='" + EDRPOU + "']"));

        System.out.println("Немає жодного платника! Створюємо.");
        $(counterPartyAddPayerButton).click();
        $(counterPartyAdd_EDRPOU_Field).shouldBe(Condition.appears).setValue(EDRPOU.toString());
        $(counterPartyAddCreateButton).click();

        try {
            $(counterPartyAdd_EDRPOU_Field).shouldBe(Condition.disappears);
        } catch (Throwable throwable) {
            Thread.sleep(1000);
            System.out.println(throwable.getCause().getMessage());
            $(counterPartyAdd_EDRPOU_Field).shouldBe(Condition.disappears);
        }

        System.out.println("СТВОРИЛИ! " + thirdPartyPayer.getText() + "; ЄДРПОУ: " + EDRPOU + ". Обираємо");
        $(thirdPartyPayer).click();
        $(confirmChooseButton).click();
    }

//======================================================================================================================

    public void setPaymentMethod(String method) {
        if (method.equals("nonCash") || method.equals("безготівковий")) {
            $(paymentMethodNonCash).click();
        }
        if (method.equals("cash") || method.equals("готівка")) {
            $(paymentMethodCash).click();
        }
    }

    private void setDateFrom(int date) throws InterruptedException {
        $(dateFrom).scrollTo().click();
        $(By.xpath("//div[@class='datetimepicker-days']//td[@class='day'][.='" + date + "']")).shouldBe(Condition.visible).click();
    }

    private void setDateTo(int date) throws InterruptedException {
        $(dateTo).scrollTo().click();
        $(By.xpath("(.//div[@class='datetimepicker-days']//td[@class='day'][.='" + date + "'])[last()]")).shouldBe(Condition.visible).click();
    }

    public void setDate(int from, int to) throws InterruptedException {
        setDateFrom(from);
        setDateTo(to);
    }

    public void setCargoType(String cargoType) {
        $(cargoTypeSelectButton).scrollTo();
        $(cargoTypeSelectButton).shouldBe(Condition.visible).click();
        String tp = "";
        if (cargoType.equals("Cargo") || cargoType.equals("вантаж")) {
            tp = "Cargo";
        }
        if (cargoType.equals("Documents") || cargoType.equals("документи")) {
            tp = "Documents";
        }
        if (cargoType.equals("Pallet") || cargoType.equals("палети")) {
            tp = "Pallet";
        }
        if (cargoType.equals("TiresWheels") || cargoType.equals("шини-диски")) {
            tp = "TiresWheels";
        }
        if (cargoType.equals("Parcel") || cargoType.equals("посилка")) {
            tp = "Parcel";
        }
        String type0 = ".//ul[@id='cargoTypesSelectBoxItOptions']/li[@data-val='" + tp + "']";
        String type1 = ".//span[@data-val='" + tp + "']";

        WebElement cargoTypeIs = $(By.xpath(type0));
        WebElement cargoIs = $(By.xpath(type1));

        try {
            $(cargoTypeIs).shouldBe(Condition.visible).click();
        } catch (Throwable th) {
            $(cargoIs).shouldBe(Condition.visible).click();
        }
    }

    public void setTotalWeight(int weight) {
        $(totalWeightInput).hover().setValue(String.valueOf(weight));
    }

    public void setCost() {
        String seats = $(seatsAmountInput).getAttribute("value");
        int cost = Integer.parseInt(seats) * 1000;
        $(costInput).scrollTo().hover().setValue(String.valueOf(cost));
    }

    public void setCost(int cost) {
        $(costInput).scrollTo().hover().setValue(String.valueOf(cost));
    }

    public void setAllTyresWheels(int count) {
        SelenideElement table = $(By.xpath(".//table[@id='tiresWheelsListTable']"));
        List<WebElement> list = table.findElements(By.xpath("//input[contains(@name,'tireWheel')]"));

        for (WebElement el : list) {
            $(el).setValue(String.valueOf(count));
        }
    }

    public void setSeatsAmount(int count) {
        $(seatsAmountInput).scrollTo().setValue(String.valueOf(count));
    }

    public void createDocument() throws InterruptedException {
        $(createDocumentButton).shouldBe(Condition.visible).scrollTo().click();
        try {
            $(createOK).should(Condition.appears).shouldBe(Condition.visible);
        } catch (ElementShould ex) {
            Thread.sleep(1000);
            $(createOK).should(Condition.appears).shouldBe(Condition.visible);
        }

        System.out.println("********************");
        System.out.println(createOK.getText());
        $(toCreatedENButton).shouldBe(Condition.visible).click();
        $(createDocumentButton).shouldBe(Condition.enabled);
    }

    public void createTemplate(String name) {
        $(createTemplateButton).shouldBe(Condition.visible).scrollTo().click();
        try {
            $(templateNameFormHeader).shouldBe(Condition.visible);
            $(templateNameFormInput).setValue(name);
            $(templateNameFormSaveButton).click();
            $(templateDescriptionModal).should(Condition.disappears);

            $(templateSavedModal).shouldBe(Condition.appears);
            $(toTemplatesListButton).click();
            $(templateSavedModal).shouldBe(Condition.disappears);

        } catch (Throwable th) {
            th.getMessage();
            $(templateNameFormInput).setValue(name);
            $(templateNameFormSaveButton).click();
            $(templateDescriptionModal).should(Condition.disappears);

            $(templateSavedModal).shouldBe(Condition.appears);
            $(toTemplatesListButton).click();
            $(templateSavedModal).shouldBe(Condition.disappears);
        }
    }

    public void createNewAddress(String street, String building) {
        fillAddressFields(street, building);
        addAddress();
    }

    public void createNewAddress(String street, String building, int flat) {
        fillAddressFields(street, building, flat);
        addAddress();
    }

    public void createNewAddress(String street, String building, int flat, String comment) {
        fillAddressFields(street, building, flat, comment);
        addAddress();
    }

    private void addAddress() {
        $(addNewAddressButton).click();
        $(addressFrame).shouldBe(Condition.disappears);
    }

    private void fillAddressFields(String street, String building) {
        $(createAddressButton).shouldBe(Condition.visible).click();
        $(addressFrame).shouldBe(Condition.appears);
        $(streetInput).setValue(street);
        $(dropdownMenu).shouldBe(Condition.appears);
        $(By.xpath("(.//ul[@class='typeahead dropdown-menu']/li[contains(@data-value,'" + street + "')]/a)[1]")).hover().click();
        $(buildingInput).setValue(building);
        $(By.xpath("(.//ul[@class='typeahead dropdown-menu']/li[contains(@data-value,'" + building + "')]/a)[1]")).hover().click();
    }

    private void fillAddressFields(String street, String building, int flat) {
        fillAddressFields(street, building);
        $(flatName).setValue(String.valueOf(flat));
    }

    private void fillAddressFields(String street, String building, int flat, String comment) {
        fillAddressFields(street, building, flat);
        $(commentName).setValue(comment);
    }

//  Создание/Редактирование контактного лица ===========================================================================
//    Якщо створюємо нового контрагента-приватну особу, то вона з'являється в списку контактних осіб.
//    Якщо створюємо нового контрагента-юридичну особу, то вона з'являється в списку контрагентів.

    public void selectOrganisation(String ownershipForm, String name) {
        String ownership = ownershipForm;
        String organisation = ".//li[@data-counterpartytype='Organization']";
        String orgName = "[@data-description='" + name + "']/a";

        //    Форми власності
        String VK = "[@data-ownershipform='7f0f3516-2519-11df-be9a-000c291af1b3']";
        String DP = "[@data-ownershipform='7f0f3515-2519-11df-be9a-000c291af1b3']";
        String KP = "[@data-ownershipform='7f0f3518-2519-11df-be9a-000c291af1b3']";
        String KT = "[@data-ownershipform='10d78dad-2352-11e2-83ab-d4ae52ab9fab']";
        String PAT = "[@data-ownershipform='361b83db-886e-11e1-a146-0026b97ed48a']";
        String PII = "[@data-ownershipform='9252696e-2202-11e4-acce-0050568002cf']";
        String PP = "[@data-ownershipform='7f0f3519-2519-11df-be9a-000c291af1b3']";
        String PrAT = "[@data-ownershipform='b0b2c790-8920-11e1-8429-0026b97ed48a']";
        String PT = "[@data-ownershipform='7f0f3514-2519-11df-be9a-000c291af1b3']";
        String SP = "[@data-ownershipform='7f0f351a-2519-11df-be9a-000c291af1b3']";
        String TDV = "[@data-ownershipform='7f0f351c-2519-11df-be9a-000c291af1b3']";
        String TOV = "[@data-ownershipform='7f0f351d-2519-11df-be9a-000c291af1b3']";
        String FG = "[@data-ownershipform='7f0f3517-2519-11df-be9a-000c291af1b3']";
        String FOP = "[@data-ownershipform='7f0f351e-2519-11df-be9a-000c291af1b3']";

        switch (ownership) {
            case "ВК":
                ownership = VK;
                break;
            case "ДП":
                ownership = DP;
                break;
            case "КР":
                ownership = KP;
                break;
            case "КТ":
                ownership = KT;
                break;
            case "ПАТ":
                ownership = PAT;
                break;
            case "ПІІ":
                ownership = PII;
                break;
            case "ПП":
                ownership = PP;
                break;
            case "ПрАТ":
                ownership = PrAT;
                break;
            case "ПТ":
                ownership = PT;
                break;
            case "СП":
                ownership = SP;
                break;
            case "ТДВ":
                ownership = TDV;
                break;
            case "ТОВ":
                ownership = TOV;
                break;
            case "ФГ":
                ownership = FG;
                break;
            case "ФОП":
                ownership = FOP;
                break;
            default:
                ownership = TOV;
                break;
        }
        String path = organisation + ownership + orgName;
        WebElement firma = $(By.xpath(path));
        System.out.println("Контора існує: " + firma.getText());
        $(firma).hover().click();
    }

//  ====================================================================================================================

    private static final WebElement setSenderButton = $(By.xpath(".//a[@id='SenderSelectButton']"));
    private static final WebElement setRecipientButton = $(By.xpath(".//a[@id='RecipientSelectButton']"));

    private static final WebElement setPayerSender = $(By.xpath(".//button[@id='SenderPayer']"));
    private static final WebElement setPayerRecipient = $(By.xpath(".//button[@id='RecipientPayer']"));
    private static final WebElement setPayerThirdPerson = $(By.xpath(".//button[@id='ThirdPersonPayer']"));
    private static final WebElement thirdPersonChangeButton = $(By.xpath(".//a[@id='ThirdPersonSelectButton']"));

    private static final WebElement paymentFormIsNonCash = $(By.xpath(".//button[@data-type='NonCash']"));
    private static final WebElement paymentFormIsCash = $(By.xpath(".//button[@data-type='Cash']"));

//  ------------------------------------------------------------- IT IS MAY BE ON ChangeCounterParty class!

    private static final WebElement warehouseInput = $(By.xpath(".//input[@id='filter_journal_address']"));
    private static final WebElement cityInput = $(By.xpath(".//input[@id='filter_journal_cities']"));
    private static final WebElement cityInputCleaner = $(By.xpath(".//input[@id='filter_journal_cities']/../div[@class='button-input clear']"));
    private static final WebElement citiesList = $(By.xpath(".//ul[@id='cities_ul']"));
    private static final WebElement addressList = $(By.xpath(".//ul[@id='address_ul']"));

    private static final WebElement elList = $(By.xpath(".//div[@class='journal_list_small']"));
    private static final String warehouses = "//li[@data-warehouse='true']";

    private static final WebElement counterPartyWindow = $(By.xpath(".//div[@id='selectCounterpartyModal']"));
    private static final WebElement counterPartyListFrame = $(By.xpath(".//ul[@id='counterparties_ul_counterparty']"));
    private static final WebElement noWritesFoundMessage = $(By.xpath(".//p[@class='alert centered empty_list']/span"));
    private static final WebElement counterPartyAddPayerButton = $(By.xpath(".//a[@id='createCounterpartyThirdPerson']"));
    private static final WebElement counterPartyAdd_EDRPOU_Field = $(By.xpath(".//input[@id='counterpartyEDRPOU']"));
    private static final WebElement counterPartyAddCreateButton = $(By.xpath(".//button[@id='counterpartyThirdPersonSaveButton']"));
    private static final WebElement confirmChooseButton = $(By.xpath(".//button[@id='selectCounterpartyOnlyButton']"));
    private static final WebElement selectCounterPartyButton = $(By.xpath(".//button[@id='selectCounterpartyButton']"));
    private static final WebElement dismissChangeCouterPartyButton = $(By.xpath(".//div[@id='selectCounterpartyModal']/div[@class='modal-footer my_control_cent']/button[@class='btn reset']"));
    private static final WebElement counterPartyWindowClose = $(By.xpath(".//div[@id='selectCounterpartyModal']/div/button[@class='close']"));
    private static final WebElement paymentMethodNonCash = $(By.xpath(".//div[@id='blockPaymentsForms']/button[@data-type='NonCash']"));
    private static final WebElement paymentMethodCash = $(By.xpath(".//div[@id='blockPaymentsForms']/button[@data-type='Cash']"));

//  ====================================================================================================================

    //  Дата відправки
    private static final WebElement dateFrom = $(By.xpath(".//*[@id='dateFrom']/span[@class='add-on btn btn-calendar']"));
    //  Бажана дата та час доставки
    private static final WebElement dateTo = $(By.xpath(".//*[@id='dateTo']/span[@class='add-on btn btn-calendar']"));

//  ====================================================================================================================

    //  Параметри відправлення (тип вантажу)
    private static final WebElement cargoTypeSelectButton = $(By.xpath(".//span[@id='cargoTypesSelectBoxItArrowContainer']"));

    //  Параметри відправлення (загальна вага)
    private static final WebElement totalWeightInput = $(By.xpath(".//input[@id='Weight']"));

    //  Параметри відправлення (об'ємна вага)
    private static final WebElement volumeWeightInput = $(By.xpath(".//input[@id='Weight']"));

    //  Параметри відправлення (загальний об'єм відправлення)
    private static final WebElement volumeGeneralInput = $(By.xpath(".//input[@id='VolumeGeneral']"));

    //  Параметри відправлення (кількість місць)
    private static final WebElement seatsAmountInput = $(By.xpath(".//input[@id='seatsAmount']"));

    //  Параметри відправлення (оголошена вартість)
    private static final WebElement costInput = $(By.xpath(".//input[@id='cost']"));

    //  Параметри відправлення (опис відправлення)
    private static final WebElement descriptionInput = $(By.xpath(".//input[@id='Description']"));

//  ====================================================================================================================

    private static final WebElement createDocumentButton = $(By.xpath(".//input[@id='submitNewOrderButton']"));
    private static final WebElement createOK = $(By.xpath(".//div[@id='documentCreatedModal']/div[h3='ЕН створено']"));
    private static final WebElement toCreatedENButton = $(By.xpath("//a[@id='createdDocumentLink']"));

    private static final WebElement createTemplateButton = $(By.xpath(".//input[@id='submitNewTemplateButton']"));

    private static final WebElement templateDescriptionModal = $(By.xpath(".//div[@id='documentTemplateDescriptionModal']"));
    private static final WebElement templateNameFormHeader = $(By.xpath(".//div[@id='documentTemplateDescriptionModal']/div[@class='modal-header']"));
    private static final WebElement templateNameFormInput = $(By.xpath(".//div[@id='documentTemplateDescriptionModal']/div[@class='modal-body']/input"));
    private static final WebElement templateNameFormSaveButton = $(By.xpath(".//a[@id='btnTemplateDescription']"));
    private static final WebElement templateNameFormCancelButton = $(By.xpath(".//a[@id='btnCancelTemplateDescription']"));

    private static final WebElement templateSavedModal = $(By.xpath(".//div[@id='documentTemplateCreatedModal']"));
    private static final WebElement cloneENButton = $(By.xpath("//a[@id='cloneDocumentTemplateLink']"));
    private static final WebElement toTemplatesListButton = $(By.xpath(".//*[@id='documentTemplateCreatedModal']//a[@href='/orders/templates/filter/clear']"));
    private static final WebElement toCreatedTemplateButton = $(By.xpath(".//a[@id='createdDocumentTemplateLink']"));

//  ====================================================================================================================

    private static final WebElement createAddressButton = $(By.xpath(".//a[@class='btn btn-np btn-second createAddress']"));
    private static final WebElement addressFrame = $(By.xpath(".//div[@id='addressModal']"));
    private static final WebElement streetInput = $(By.xpath(".//input[@id='streetName']"));
    private static final WebElement dropdownMenu = $(By.xpath(".//ul[@class='typeahead dropdown-menu']"));
    private static final WebElement buildingInput = $(By.xpath(".//input[@id='buildingName']"));
    private static final WebElement flatName = $(By.xpath(".//input[@id='flatName']"));
    private static final WebElement commentName = $(By.xpath(".//input[@id='commentName']"));
    private static final WebElement addNewAddressButton = $(By.xpath(".//button[@id='counterpartyAddressSaveButton']"));

//  ====================================================================================================================

    private static final WebElement closeFrame = $(By.xpath(".//div[contains(@style,'display: block')][@aria-hidden='false']/div/button[@class='close']"));
}
