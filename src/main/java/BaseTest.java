import org.testng.annotations.BeforeMethod;
import steps.Steps;

public class BaseTest implements Steps {

    String baseURL;
    String createUserJSON;
    String updateUserJSON;
    String registrationSuccessfulJSON;
    String registrationUnsuccessfulJSON;
    String authorizationSuccessfulJSON;
    String authorizationUnsuccessfulJSON;


    @BeforeMethod
    public void bfM(){
        baseURL = "https://reqres.in/";
        createUserJSON = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        updateUserJSON = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        registrationSuccessfulJSON = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";
        registrationUnsuccessfulJSON = "{\"email\": \"sydney@fife\"}";
        authorizationSuccessfulJSON = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        authorizationUnsuccessfulJSON = "{\"email\": \"peter@klaven\"}";
    }

}
