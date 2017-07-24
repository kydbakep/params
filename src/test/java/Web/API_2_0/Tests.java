package Web.API_2_0;

import Web.API_2_0.Classes.Generator;
import Web.API_2_0.Classes.RequestSender;
import Web.API_2_0.Classes.ResponseAnalyser;
import Web.API_2_0.Classes.ResponseParser;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
//import ua.novaposhta.web.webclient.Main;

import java.io.IOException;

/**
 * Created by tober on 31.05.17.
 * Tests for json requests to API 2.0
 */

public class Tests {
    @Before
    public void setup() {
    }

////    @Test
//    public void postRequest00(){
//        Request request = new Request();
//        request.setFilePath("/storage/develop/projects/idea/awis/src/test/java/Web/API_2_0/json");
//        request.setJsonType("/corporate/");
//        request.setUrl("https://api.novaposhta.ua/v2.0/json/");
//
//        request.setFileName("createRegister");
//        try {
//            request.postJson();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
////    @Test //Создание ИД на адрес, строкой
//    public void postRequest01(){
//        Request request = new Request();
//        request.setFilePath("/storage/develop/project/idea/awis/src/test/java/Web/API_2_0/json");
//        request.setJsonType("/corporate/");
//        request.setUrl("https://api.novaposhta.ua/v2.0/json/");
//
//        request.setFileName("createCounterPartyRecipientOrganisation");
//        try {
//            request.postJson();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
////    @Test //Создание ИД на адрес, строкой
//    public void postRequest02(){
//        Request request = new Request();
//        request.setFilePath("/storage/develop/project/idea/awis/src/test/java/Web/API_2_0/json");
//        request.setJsonType("/loyalty/");
//        request.setUrl("https://api.novaposhta.ua/v2.0/json/");
//
//        request.setFileName("createInternetDocumentToAddress");
//        try {
//            request.postJson();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //    @Test //Тест парсера
    public void testParser() throws IOException, ParseException, JSONException {
        ResponseParser parser = new ResponseParser();
        parser.setNode(parser.getKey("data", "ContactPerson", "data"));
        System.out.println(parser.getKey("Ref"));
        System.out.println(parser.getKey("success"));
        System.out.println(parser.getKey("Description", 1));
        System.out.println(parser.getKey("Description", 2));
        System.out.println(parser.getKey("Description", 3));
        parser.setNode(parser.getKey("data"));
        System.out.println(parser.getKey());
        System.out.println(parser.getKey("ContactPerson"));
    }

    @Test //Тест аналізатора
    public void testAnalyser() throws IOException, ParseException, JSONException {
        RequestSender sender = new RequestSender();
        ResponseAnalyser analyser = new ResponseAnalyser();
        sender.sendRequest("corporate", "createCounterPartyRecipientPerson");
        analyser.printResponse();
        analyser.printWarning();
    }

    @Test //Тест листа нодів
    public void testList() throws IOException, ParseException, JSONException {
//        ResponseParser responseParser = new ResponseParser();
//        System.out.println(parser.getResponseNodes().get("data"));
//        parser.getNodes();
//        RequestSender sender = new RequestSender();
//        sender.sendRequest("corporate","createRegister");
//        JSONParser parser = new JSONParser();
//        JSONObject response = (JSONObject) parser.parse(new FileReader("./src/test/java/Web/API_2_0/json/response/response.json"));
//        new JsonChecker(response.toJSONString());
//        new JsonChecker_2(response.toJSONString());

//        Parser parser = new Parser();
//        parser.parse();
//        parser.printAllDataNodes();
    }

    @Test // Отримуэмо API ключ
    public void getApiKey() throws IOException, ParseException, JSONException {
        RequestSender sender = new RequestSender();
        sender.sendRequest("loyalty", "generateApiKey");
        ResponseParser parser = new ResponseParser();
        parser.setNode(parser.getKey("data", "ApiKey"));
        System.out.println("API key for loyal user: " + parser.getKey());

        sender.sendRequest("corporate", "generateApiKey");
        parser.setNode(parser.getKey("data", "ApiKey"));
        System.out.println("API key for corporate user: " + parser.getKey());
    }

    @Test // Отримуємо API ключ за допомогою JSON запиту
    public void getKey() throws ParseException, JSONException, IOException {
        Generator generator = new Generator();
        System.out.println(generator.getKey("loyalty"));
        System.out.println(generator.getKey("corporate"));
    }

//    @Test // Передаємо параметр ззовні, через консольну команду
//    public void consoleTest(){
//        Main consoleTest = new Main();
//        consoleTest.getProperties("web");
    }

