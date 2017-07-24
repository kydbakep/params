package Web.API_2_0.Classes;

import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by tober on 29.06.17.
 * Response analyser for Response Parser
 */
public class ResponseAnalyser {
    private ResponseParser parser = new ResponseParser();

    public ResponseAnalyser() throws IOException, ParseException {
    }

    public void printResponse() throws IOException, ParseException {
        System.out.println(parser.getResponse("string"));
        System.out.println("Success: " + parser.getKey("success"));
    }

    public void printErrors() throws IOException, ParseException {
        System.out.println(parser.getKey("errors"));
    }

    public void printWarnings() throws IOException, ParseException {

        String s = parser.getKey("warnings");
        if(s.length()<=2){
            System.out.println("no warnings");
        } else if (s.length()>2){
            System.out.println(s);
        }

        parser.setNode(parser.getKey("data","ContactPerson","warnings"));
        System.out.println(parser.getKey());
    }

    public void printWarning() throws IOException, ParseException {

        parser.setNode(parser.getKey("data", "ContactPerson","warnings"));
        System.out.println(parser.getKey());

    }
}



