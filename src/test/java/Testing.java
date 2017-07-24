import org.junit.Test;

public class Testing {
    public Testing(){}

    @Test
    public void testProperties(){
        DefineParameters defineParameters = new DefineParameters();
        defineParameters.getProperty("url");
        defineParameters.getProperty("name");
        defineParameters.getProperty("lastName");
    }

    @Test
    public void anotherTest(){
        DefineParameters defineParameters = new DefineParameters();
        defineParameters.getProperty("name");
    }
}
