package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import models.RequestModels.UserRequest;
import models.ResponseModels.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class API_Steps {
    @Step("Получение списка пользователей")
    public UsersListResponse getUserList(String baseURL){
        return given()
                .baseUri(baseURL)
                .when()
                .get("/api/users?page=2")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract().body().as(UsersListResponse.class);
    }

    @Step("Получение одного польвателя")
    public SingleUserResponse getSingleUser(String baseURL, int id, Integer statusCode){
        return given()
                .baseUri(baseURL)
                .when()
                .get("/api/users/" + id)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(statusCode)
                .extract().body().as(SingleUserResponse.class);
    }

    @Step("Получение списка пользователей, но по другому")
    public void getDelayedResponse(String baseURL){
        given()
                .baseUri(baseURL)
                .when()
                .get("/api/users?delay=3")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract().body().as(UsersListResponse.class);
    }

    @Step("Получение списка ресурсов")
    public ResourceListResponse getResourceList(String baseURL){
        return given()
                .baseUri(baseURL)
                .when()
                .get("/api/unknown")
                .then()
                .statusCode(200)
                .extract().body().as(ResourceListResponse.class);
    }

    @Step("Получение одного ресурса")
    public SingleResourceResponse getSingleResource(String baseURL, int id, Integer statusCode){
        return given()
                .baseUri(baseURL)
                .when()
                .get("/api/unknown/" + id)
                .then()
                .statusCode(statusCode)
                .extract().body().as(SingleResourceResponse.class);
    }

    @Step("Создание пользователя")
    public AccountResponse createUser(String baseURL, UserRequest body){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .extract().body().as(AccountResponse.class);
    }

    @Step("Обновление пользователя (update)")
    public AccountResponse updateUser(String baseURL, UserRequest body, int id){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(body)
                .when()
                .put("/api/users/" + id)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().body().as(AccountResponse.class);
    }

    @Step("Обновление пользователя (patch)")
    public AccountResponse patchUser(String baseURL, UserRequest body, int id){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(body)
                .when()
                .patch("/api/users/" + id)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().body().as(AccountResponse.class);
    }

    @Step("Удаление пользователя")
    public void deleteUser(String baseURL, int id){
        given()
                .baseUri(baseURL)
                .when()
                .delete("/api/users/" + id)
                .then()
                .statusCode(204);
    }

    @Step("Регистрация пользователя")
    public void registrationSuccessful(String baseURL, String registrationSuccessfulJson){
        given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(registrationSuccessfulJson)
                .when()
                .post("/api/register")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Step("Регистрация пользователя (провал)")
    public void registrationUnsuccessful(String baseURL, String registrationUnsuccessfulJson){
        given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(registrationUnsuccessfulJson)
                .when()
                .post("/api/register")
                .then()
                .statusCode(400)
                .contentType("application/json")
                .body("error", equalTo("Missing password"));
    }

    @Step("Авторизация пользователя")
    public void authorizationSuccessful(String baseURL, String authorizationSuccessfulJson){
        given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(authorizationSuccessfulJson)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Step("Авторизация пользователя")
    public void authorizationUnsuccessful(String baseURL, String authorizationUnsuccessfulJson){
        given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(authorizationUnsuccessfulJson)
                .when()
                .post("/api/register")
                .then()
                .statusCode(400)
                .contentType("application/json")
                .body("error", equalTo("Missing password"));
    }
}
