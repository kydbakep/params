import org.junit.Test;

public class ParametersTesting {
    public ParametersTesting(){}

    @Test
    public void testProperties(){
        System.out.println("...1");
        DefineParameters defineParameters = new DefineParameters();
        defineParameters.getProperty("url");
        defineParameters.getProperty("name");
        defineParameters.getProperty("lastName");
    }

    @Test
    public void anotherTest(){
        System.out.println("...2");
        DefineParameters defineParameters = new DefineParameters();
        defineParameters.getProperty("name");
    }
}
