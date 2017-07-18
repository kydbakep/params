import java.io.FileInputStream;
import java.util.Properties;

public class Methods {
    public Methods(){}

    public void printProppertyValue(String property){
        Properties prop = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/resources/fucking.properties");
            prop.load(inputStream);
        } catch (java.io.IOException e) {
            System.out.println("properties file not found!");
        }

        String query = prop.getProperty(property);
        System.out.println(query);
    }

    public String getPropertyValue(String property){
        Properties prop = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/resources/fucking.properties");
            prop.load(inputStream);
        } catch (java.io.IOException e) {
            System.out.println("properties file not found!");
        }

        return prop.getProperty(property);
    }
}
