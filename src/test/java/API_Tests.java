import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Epic;
import models.RequestModels.AuthRegRequest;
import models.RequestModels.UserRequest;
import models.ResponseModels.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.API_Steps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Epic("Тестировние API reqres.in")
public class API_Tests extends BaseTest{
    @Test(description = "Тестирование получения пользователей")
    public void userTests(){
        List<DataResponse> usersListResponse = API_STEPS.getUserList(baseURL).getData();
        List<DataResponse> checkUserList = new ArrayList<>();
        DataResponse user1ElementList = new DataResponse(7, "michael.lawson@reqres.in", "Michael", "Lawson", "https://reqres.in/img/faces/7-image.jpg");
        DataResponse user2ElementList = new DataResponse(8, "lindsay.ferguson@reqres.in", "Lindsay", "Ferguson", "https://reqres.in/img/faces/8-image.jpg");
        DataResponse user3ElementList = new DataResponse(9, "tobias.funke@reqres.in", "Tobias", "Funke", "https://reqres.in/img/faces/9-image.jpg");
        DataResponse user4ElementList = new DataResponse(10, "byron.fields@reqres.in", "Byron", "Fields", "https://reqres.in/img/faces/10-image.jpg");
        DataResponse user5ElementList = new DataResponse(11, "george.edwards@reqres.in", "George", "Edwards", "https://reqres.in/img/faces/11-image.jpg");
        DataResponse user6ElementList = new DataResponse(12, "rachel.howell@reqres.in", "Rachel", "Howell", "https://reqres.in/img/faces/12-image.jpg");
        checkUserList.add(user1ElementList);
        checkUserList.add(user2ElementList);
        checkUserList.add(user3ElementList);
        checkUserList.add(user4ElementList);
        checkUserList.add(user5ElementList);
        checkUserList.add(user6ElementList);
        Assert.assertEquals(usersListResponse, checkUserList);
        DataResponse singleUserResponse = API_STEPS.getSingleUser(baseURL, 2, 200).getData();
        DataResponse checkSingleUser = new DataResponse(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg");
        Assert.assertEquals(singleUserResponse, checkSingleUser);
        DataResponse singleUserFailResponse = API_STEPS.getSingleUser(baseURL, 23, 404).getData();
        Assert.assertEquals(singleUserFailResponse, null);
        List<DataResponse> usersListDelayedResponse = API_STEPS.getDelayedResponse(baseURL).getData();
        List<DataResponse> checkUserListDelayed = new ArrayList<>();
        DataResponse delayed1ElementList = new DataResponse(1, "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg");
        DataResponse delayed2ElementList = new DataResponse(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg");
        DataResponse delayed3ElementList = new DataResponse(3, "emma.wong@reqres.in", "Emma", "Wong", "https://reqres.in/img/faces/3-image.jpg");
        DataResponse delayed4ElementList = new DataResponse(4, "eve.holt@reqres.in", "Eve", "Holt", "https://reqres.in/img/faces/4-image.jpg");
        DataResponse delayed5ElementList = new DataResponse(5, "charles.morris@reqres.in", "Charles", "Morris", "https://reqres.in/img/faces/5-image.jpg");
        DataResponse delayed6ElementList = new DataResponse(6, "tracey.ramos@reqres.in", "Tracey", "Ramos", "https://reqres.in/img/faces/6-image.jpg");
        checkUserListDelayed.add(delayed1ElementList);
        checkUserListDelayed.add(delayed2ElementList);
        checkUserListDelayed.add(delayed3ElementList);
        checkUserListDelayed.add(delayed4ElementList);
        checkUserListDelayed.add(delayed5ElementList);
        checkUserListDelayed.add(delayed6ElementList);
        Assert.assertEquals(usersListDelayedResponse, checkUserListDelayed);
    }

    @Test(description = "Тестировние полчения ресурсов")
    public void resourceTests(){
        List<DataResourceResponse> resourceList =  API_STEPS.getResourceList(baseURL).getData();
        List<DataResourceResponse> checkResourceList = new ArrayList<DataResourceResponse>();
        DataResourceResponse resource1Response = new DataResourceResponse(1, "cerulean", 2000, "#98B2D1","15-4020");
        DataResourceResponse resource2Response = new DataResourceResponse(2, "fuchsia rose", 2001, "#C74375","17-2031");
        DataResourceResponse resource3Response = new DataResourceResponse(3, "true red", 2002, "#BF1932","19-1664");
        DataResourceResponse resource4Response = new DataResourceResponse(4, "aqua sky", 2003, "#7BC4C4","14-4811");
        DataResourceResponse resource5Response = new DataResourceResponse(5, "tigerlily", 2004, "#E2583E","17-1456");
        DataResourceResponse resource6Response = new DataResourceResponse(6, "blue turquoise", 2005, "#53B0AE","15-5217");
        checkResourceList.add(resource1Response);
        checkResourceList.add(resource2Response);
        checkResourceList.add(resource3Response);
        checkResourceList.add(resource4Response);
        checkResourceList.add(resource5Response);
        checkResourceList.add(resource6Response);
        Assert.assertEquals(resourceList, checkResourceList);
        DataResourceResponse getSingleResource = API_STEPS.getSingleResource(baseURL, 2, 200).getData();
        DataResourceResponse checkSingleResource = new DataResourceResponse(2, "fuchsia rose", 2001, "#C74375","17-2031");
        Assert.assertEquals(getSingleResource, checkSingleResource);
        DataResourceResponse getSingleResourceFail = API_STEPS.getSingleResource(baseURL, 77, 404).getData();
        Assert.assertEquals(getSingleResourceFail, null);
    }

    @Test(description = "Тестирование CRUD пользователя")
    public void CRUDTests(){
        UserRequest userRequest = new UserRequest("morpheus", "leader");
        AccountResponse accountCreate = API_STEPS.createUser(baseURL, userRequest);
        String createdAt = accountCreate.getCreatedAt();
        String id = accountCreate.getId();
        AccountResponse checkAccountCreate = new AccountResponse("morpheus", "leader", id, createdAt, null);
        Assert.assertEquals(accountCreate, checkAccountCreate);
        UserRequest userUpdateRequest = new UserRequest("morpheus", "zion resident");
        AccountResponse accountUpdate = API_STEPS.updateUser(baseURL, userUpdateRequest, 2);
        String updatedAt = accountUpdate.getUpdatedAt();
        AccountResponse checkAccountUpdate = new AccountResponse("morpheus", "zion resident", null, null, updatedAt);
        Assert.assertEquals(accountUpdate, checkAccountUpdate);
        AccountResponse accountPatch = API_STEPS.patchUser(baseURL, userUpdateRequest, 2);
        String patchedAt = accountPatch.getUpdatedAt();
        AccountResponse checkAccountPatch = new AccountResponse("morpheus", "zion resident", null, null, patchedAt);
        Assert.assertEquals(accountPatch, checkAccountPatch);
        API_STEPS.deleteUser(baseURL, 2);
    }

    @Test(description = "Тестирование авторизации и регистрации")
    public void AuthRegTest(){
        AuthRegRequest regSuccessfulRequest = new AuthRegRequest("eve.holt@reqres.in", "pistol");
        AuthRegResponse successfulRegistration = API_STEPS.registrationSuccessful(baseURL, regSuccessfulRequest, 200);
        AuthRegResponse checkSuccessfulRegistration = new AuthRegResponse(4, "QpwL5tke4Pnpja7X4", null);
        Assert.assertEquals(successfulRegistration, checkSuccessfulRegistration);
        AuthRegRequest authSuccessfulRequest = new AuthRegRequest("eve.holt@reqres.in", "cityslicka");
        AuthRegResponse successfulAuthorization = API_STEPS.authorizationSuccessful(baseURL, authSuccessfulRequest, 200);
        AuthRegResponse checkSuccessfulAuthorization = new AuthRegResponse(null, "QpwL5tke4Pnpja7X4", null);
        Assert.assertEquals(successfulAuthorization, checkSuccessfulAuthorization);
        AuthRegRequest regUnsuccessfulRequest = new AuthRegRequest("sydney@fife", null);
        AuthRegResponse unsuccessfulRegistration = API_STEPS.registrationSuccessful(baseURL, regUnsuccessfulRequest, 400);
        AuthRegResponse checkUnsuccessfulRegistration = new AuthRegResponse(null, null, "Missing password");
        Assert.assertEquals(unsuccessfulRegistration, checkUnsuccessfulRegistration);
        AuthRegRequest authUnsuccessfulRequest = new AuthRegRequest("peter@klaven", null);
        AuthRegResponse unsuccessfulAuthorization = API_STEPS.authorizationSuccessful(baseURL, authUnsuccessfulRequest, 400);
        AuthRegResponse checkUnsuccessfulAuthorization = new AuthRegResponse(null, null, "Missing password");
        Assert.assertEquals(unsuccessfulAuthorization, checkUnsuccessfulAuthorization);
    }
}
