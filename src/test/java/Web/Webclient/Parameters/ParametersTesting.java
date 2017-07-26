package Web.Webclient.Parameters;

import org.junit.Test;

import java.io.FileNotFoundException;

public class ParametersTesting {
    public ParametersTesting(){}

    @Test
    public void testProperties() throws FileNotFoundException {
        System.out.println("...1");
        DefineParameters defineParameters = new DefineParameters();
        System.out.println(defineParameters.getParameter("name"));
    }

    @Test
    public void anotherTest() throws FileNotFoundException {
        System.out.println("...2");
        DefineParameters defineParameters = new DefineParameters();
        System.out.println(defineParameters.getParameter("lastName"));
    }
}
