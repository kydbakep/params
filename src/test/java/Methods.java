import java.io.FileInputStream;
import java.util.Properties;

class Methods {
    Methods() {
    }

    private String setProperty(String value) {
        return System.getProperty(value);
    }

    void getProperty(String value) {
        Properties prop = new Properties();
        if (setProperty(value) == null) {
            System.out.print("property '" + value + "' not defined. using from config: ");
            try {
                FileInputStream inputStream = new FileInputStream("./src/test/resources/predefined.properties");
                prop.load(inputStream);
                System.out.println(prop.getProperty(value) + "\n");
            } catch (java.io.IOException e) {
                System.out.println("properties file not found!");
            }
        } else System.out.println("property '" + value + "' is user defined: " + setProperty(value));
    }
}