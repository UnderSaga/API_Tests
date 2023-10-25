package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import models.RequestModels.AuthRegRequest;
import models.RequestModels.UserRequest;
import models.ResponseModels.*;

import static io.restassured.RestAssured.given;
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
    public UsersListResponse getDelayedResponse(String baseURL){
        return given()
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
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
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
    public AuthRegResponse registrationSuccessful(String baseURL, AuthRegRequest authRegRequest, Integer statusCode){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(authRegRequest)
                .when()
                .post("/api/register")
                .then()
                .statusCode(statusCode)
                .contentType("application/json")
                .extract().body().as(AuthRegResponse.class);
    }

    @Step("Авторизация пользователя")
    public AuthRegResponse authorizationSuccessful(String baseURL, AuthRegRequest authRegRequest, Integer statusCode){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(authRegRequest)
                .when()
                .post("/api/login")
                .then()
                .statusCode(statusCode)
                .contentType("application/json")
                .extract().body().as(AuthRegResponse.class);
    }
}
