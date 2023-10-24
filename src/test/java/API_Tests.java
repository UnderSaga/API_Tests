import io.qameta.allure.Epic;
import models.RequestModels.UserRequest;
import org.testng.annotations.Test;
import steps.API_Steps;

@Epic("Тестировние API reqres.in")
public class API_Tests extends BaseTest{
    @Test(description = "Тестирование получения пользователей")
    public void userTests(){
        API_STEPS.getUserList(baseURL);
        API_STEPS.getSingleUser(baseURL, 2, 200);
        API_STEPS.getSingleUser(baseURL, 23, 404);
        API_STEPS.getDelayedResponse(baseURL);
    }

    @Test(description = "Тестировние полчения ресурсов")
    public void resourceTests(){
        API_STEPS.getResourceList(baseURL);
        API_STEPS.getSingleResource(baseURL, 2, 200);
        API_STEPS.getSingleResource(baseURL, 77, 404);
    }

    @Test(description = "Тестирование CRUD пользователя")
    public void CRUDTests(){
        UserRequest userRequest = new UserRequest("morpheus", "leader");
        API_STEPS.createUser(baseURL, userRequest);
        UserRequest userUpdateRequest = new UserRequest("morpheus", "zion resident");
        API_STEPS.updateUser(baseURL, userUpdateRequest, 2);
        API_STEPS.patchUser(baseURL, userUpdateRequest, 2);
        API_STEPS.deleteUser(baseURL, 2);
    }

    @Test(description = "Тестирование авторизации и регистрации")
    public void AuthRegTest(){
        API_STEPS.registrationSuccessful(baseURL, registrationSuccessfulJSON);
        API_STEPS.registrationUnsuccessful(baseURL, registrationUnsuccessfulJSON);
        API_STEPS.authorizationSuccessful(baseURL, authorizationSuccessfulJSON);
        API_STEPS.authorizationUnsuccessful(baseURL, authorizationUnsuccessfulJSON);
    }
}
