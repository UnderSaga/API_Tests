import org.testng.annotations.BeforeMethod;
import steps.Steps;

public class BaseTest implements Steps {

    String baseURL;


    @BeforeMethod
    public void bfM(){
        baseURL = "https://reqres.in/";
    }

}
