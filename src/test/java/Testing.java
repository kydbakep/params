import org.junit.Test;

public class Testing {
    public Testing(){}

    @Test
    public void testPropperties(){
        Methods methods = new Methods();
        methods.printProppertyValue("name");
        System.out.println(methods.getPropertyValue("lastname"));
    }
}
