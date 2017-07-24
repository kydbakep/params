package Web.Webclient;

import Web.Webclient.Methods.CounterParties;
import Web.Webclient.Methods.RegistrationAlertsCheck;
import Web.Webclient.Pages.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementNotFound;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

/**
 * Created by tober on 29.05.17.
 * Webclient tests
 */
public class Tests {

    private CounterParties cp = new CounterParties();

    @Before
    public void setBrowser() {
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

    @Test // логін як корпорат
    public void loginAsCorporate() throws InterruptedException {
        AuthPage authPage = new AuthPage();
        MainPage mainPage = new MainPage();
        authPage.setUrl("http://webclient.sb.np.ua");
        authPage.loginToWebclient("corporate");
        mainPage.acceptInformMessage();
        mainPage.checkUserType();
    }

    @Test // логін як лояльник
    public void loginAsLoyal() throws InterruptedException {
        AuthPage authPage = new AuthPage();
        MainPage mainPage = new MainPage();
        authPage.setUrl("http://webclient.sb.np.ua");
        authPage.loginToWebclient("loyal");
        mainPage.acceptInformMessage();
        mainPage.checkUserType();
    }


    @Test
    public void alertHandle() throws InterruptedException {
        AuthPage authPage = new AuthPage();
        authPage.setUrl("http://webclient.sb.np.ua");
        authPage.startRegistration();
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.setFirstName("Петро");
        registrationPage.setPhone("0688888888");
        registrationPage.setCity("Чорноморськ");
        registrationPage.acceptTerms();
        registrationPage.pressRegisterButton();

        RegistrationAlertsCheck registrationAlerts = new RegistrationAlertsCheck();
        registrationAlerts.checkAllAlerts();
        logout();
    }

    @Test
    public void register() throws ParseException {
        AuthPage authPage = new AuthPage();
        authPage.setUrl("http://webclient.sb.np.ua");
        authPage.startRegistration();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.setCity("Чорноморськ");
        registrationPage.setLastName("Пупкін");
        registrationPage.setFirstName("Василь");
        registrationPage.setMiddleName("Григорович");
        registrationPage.setPhone("0677777777");
        registrationPage.setEmail("pupkien@gmail.com");
        registrationPage.setSex("m");
        registrationPage.setBirthday(1982, 6, 11);
        registrationPage.setPassport("СН", "068516");
        registrationPage.acceptTerms();
        registrationPage.pressRegisterButton();

        ActivationPage activationPage = new ActivationPage();
        System.out.println(activationPage.getInfo());
    }

    @Test
    public void passwordRecovery() {
        AuthPage authPage = new AuthPage();
        authPage.setUrl("http://webclient.sb.np.ua");
        authPage.recoverPassword();

        PasswordRecoveryPage recoveryPage = new PasswordRecoveryPage();
        recoveryPage.getInfo();
//        recoveryPage.clickForgotNumber();
        recoveryPage.clickRecoveryButton();
        recoveryPage.getAlertInfo();
    }

    @Test //Создание ЕН и|или создание шаблона
    public void createEN() throws InterruptedException {
        loginAsLoyal();
        MainPage mainPage = new MainPage();
        mainPage.startCreateEN();
        CreateENPage createEN = new CreateENPage();
        createEN.changeSender("Київ", 22, "380938607233");
        createEN.changeRecipient("Бровари", 1, "380931112233");
        createEN.setPayer("третя особа", 22079373);
        createEN.setDate(28, 30);
        createEN.setCargoType("документи");
        createEN.setAllTyresWheels(6);
        createEN.setSeatsAmount(1);
        createEN.setCost(100);
        createEN.createDocument();
//        createEN.createTemplate("ШАБЛОН шаблонович бай автотест");
    }

    @Test // Створення адреси відправника
    public void createSenderAddress() throws InterruptedException {
        loginAsLoyal();
        MainPage mainPage = new MainPage();
        mainPage.startCreateEN();
        CreateENPage createEN = new CreateENPage();
        createEN.clickChangeSenderButton();
        createEN.createNewAddress("Кибальчича", "31", 153, "Коментарчикчик");
    }

//======================================================================================================================

    @Test // Створення фізика
    public void createPerson() throws InterruptedException {
        loginAsLoyal();
        MainPage mainPage = new MainPage();
        mainPage.startCreateEN();
        CreateENPage createEN = new CreateENPage();
        createEN.clickChangeRecipientButton();
        createEN.selectCity("Львів");
        cp.createNewCounterparty("Чорний", "Плащ", "380507777777");
        cp.createNewCounterparty("Пух", "Вініамін", "380333333333");
        cp.createNewCounterparty("Вовк", "Сірий", "Кликович", "380445555555", "vovk@gmail.com");
    }

    @Test // Редагування фізика (контактної особи)
    public void editPerson() throws InterruptedException {
        loginAsLoyal();
        MainPage mainPage = new MainPage();
        mainPage.startCreateEN();
        CreateENPage createEN = new CreateENPage();
        createEN.clickChangeRecipientButton();
        createEN.selectCity("Одеса");
        cp.createNewCounterparty("МакДак", "Скрудж", "380677777777");
        cp.selectPerson("МакДак Скрудж");
        cp.editPerson("МакДак", "Скрудж", "БіллГейтсович", "0999999999", "billion@gmail.com");
//        createEN.selectPerson("Вовк","Сірий");
//        createEN.selectPerson("Вініамін","Пух", "380333333333");
    }

    @Test // Видалення фізика (контактної особи)
    public void removePerson() throws InterruptedException {
        loginAsLoyal();
        MainPage mainPage = new MainPage();
        mainPage.startCreateEN();
        CreateENPage createEN = new CreateENPage();
        createEN.clickChangeRecipientButton();
        createEN.selectCity("Одеса");
        cp.createNewCounterparty("МакДак", "Скрудж", "380677777777");
        cp.removePerson("МакДак", "Скрудж", "380677777777");
    }

    @Test // Створення юрика
    public void createOrganisation() throws InterruptedException {
        loginAsLoyal();
        MainPage mainPage = new MainPage();
        mainPage.startCreateEN();
        CreateENPage createEN = new CreateENPage();
        createEN.clickChangeRecipientButton();
        createEN.selectCity("Київ");
        cp.createNewCounterparty("ТОВ", "Тобер тестінг");
        cp.createNewCounterparty("СП", "Жаба квакшинг");
        cp.createNewCounterparty("ДП", "Задрот лімітед");
    }

    @Test // Редагування юрика
    public void editOrganisation() throws InterruptedException {
        loginAsLoyal();
        MainPage mainPage = new MainPage();
        mainPage.startCreateEN();
        CreateENPage createEN = new CreateENPage();
        createEN.clickChangeRecipientButton();
        createEN.selectCity("Київ");
        cp.createNewCounterparty("СП", "Жаба квакшинг");
        cp.editOrganisation("СП", "Жаба квакшинг", "ТОВ", "Жаба квакшинг ЛТД");
    }

    @Test // Видалення юрика
    public void removeOrganisation() throws InterruptedException {
        loginAsLoyal();
        MainPage mainPage = new MainPage();
        mainPage.startCreateEN();
        CreateENPage createEN = new CreateENPage();
        createEN.clickChangeRecipientButton();
        createEN.selectCity("Одеса");
        try {
            cp.removeOrganisation("ТОВ", "Жаба квакшинг ЛТД");
        } catch (ElementNotFound notFound) {
            cp.removeOrganisation("СП", "Жаба квакшинг");
        }
    }


}
