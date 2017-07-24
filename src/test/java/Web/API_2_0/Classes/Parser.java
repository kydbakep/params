package Web.API_2_0.Classes;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tober on 07.07.17.
 * v 2
 */
public class Parser {

    private JSONParser parser = new JSONParser();
    private static final String JSON_PATH = "./src/test/java/Web/API_2_0/json/response/response.json";
    private JSONObject response = (JSONObject) parser.parse(new FileReader(JSON_PATH));

    private ArrayList<JSONArray> arrays = new ArrayList<>();
    private ArrayList<JSONObject> objects = new ArrayList<>();

    public Parser() throws IOException, ParseException {
    }

    public void parse() throws IOException, ParseException {
//        System.out.println(response.getClass());
//        System.out.println(response.get("success"));
//        System.out.println(response.get("data").getClass());
//        System.out.println(response.keySet());
//        JSONArray array = (JSONArray) response.get("data");
//        System.out.println(array.get(0));

        Iterator it = response.keySet().iterator();

        for (int i = 0; i < response.keySet().size(); i++) {
            String key = (String) it.next();
//            System.out.println(key + ": " + response.get(key) + " class: " + response.get(key).getClass());
            if (response.get(key).getClass().equals(JSONArray.class) && response.get(key).toString().length() > 2)
//                    ||
//                    response.get(key).getClass().equals(Boolean.class))
            {
                System.out.println(response.get(key));
                JSONArray newarr = (JSONArray) response.get(key);
//                System.out.println(newarr.get(0));
                objects.add((JSONObject) newarr.get(0));

            } else System.out.println("*" + response.get(key));
        }
    }

    public void printAllDataNodes() throws ParseException {
        System.out.println("**************************************************");
        for (int i = 0; i < objects.size(); i++) {
            System.out.println("Response body object: " + i + ": " + objects.get(i));
            if (objects.get(i).get("Errors").toString().length()>2){
                String key = objects.get(i).get("Errors").toString();
                System.out.println(key);
            }
            if (objects.get(i).get("Warnings").toString().length()>2){
                String key = objects.get(i).get("Warnings").toString();
                System.out.println(key);
            }
        }
    }
}
