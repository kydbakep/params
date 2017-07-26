package Web.Webclient.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class DefineParameters {
    public DefineParameters() {
    }

    private String getProperty(String value) {
        return System.getProperty(value);
    }

    private void setProperty(String value) {
        Properties prop = new Properties();
        if (getProperty(value) == null) {
            System.out.print("property '" + value + "' not defined. using from config: ");
            try {
                FileInputStream inputStream = new FileInputStream("./src/test/resources/predefined.properties");
                prop.load(inputStream);
                System.out.println(prop.getProperty(value) + "\n");
            } catch (java.io.IOException e) {
                System.out.println("properties file not found!");
            }
        } else System.out.println("property '" + value + "' is user defined: " + getProperty(value));
    }

    public String getParameter(String value) throws FileNotFoundException {
        Properties prop = new Properties();
        if (getProperty(value) == null) {
            try {
                FileInputStream inputStream = new FileInputStream("./src/test/resources/predefined.properties");
                prop.load(inputStream);
                return prop.getProperty(value);
            } catch (java.io.IOException e) {
                System.out.println("properties file not found!");
                return null;
            }
        } else {
            return getProperty(value);
        }
    }
}