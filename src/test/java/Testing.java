import org.junit.Test;

public class Testing {
    public Testing(){}

    @Test
    public void testProperties(){
        Methods methods = new Methods();
        methods.getProperty("url");
        methods.getProperty("name");
        methods.getProperty("lastName");
    }
}
