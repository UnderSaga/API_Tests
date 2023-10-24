package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class API_Steps {
    @Step("Получение списка пользователей")
    public void getUserList(String baseURL){
        given()
                .baseUri(baseURL)
                .when()
                .get("/api/users?page=2")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("data[0].id", equalTo(7))
                .body("data[0].first_name", equalTo("Michael"))
                .body("data[0].last_name", equalTo("Lawson"))
                .body("data[0].avatar", equalTo("https://reqres.in/img/faces/7-image.jpg"));
    }

    @Step("Получение одного польвателя")
    public void getSingleUser(String baseURL, int id){
        given()
                .baseUri(baseURL)
                .when()
                .get("/api/users/" + id)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
    }

    @Step("Пользователь не найден")
    public void singleUserNotFound(String baseURL, int id){
        given()
                .baseUri(baseURL)
                .when()
                .get("/api/users/" + id)
                .then()
                .statusCode(404);
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
                .body("data[0].id", equalTo(1))
                .body("data[0].first_name", equalTo("George"))
                .body("data[0].last_name", equalTo("Bluth"))
                .body("data[0].avatar", equalTo("https://reqres.in/img/faces/1-image.jpg"))
                .time(lessThan(4000L));
    }

    @Step("Получение списка ресурсов")
    public void getResourceList(String baseURL){
        given()
                .baseUri(baseURL)
                .when()
                .get("/api/unknown")
                .then()
                .statusCode(200)
                .body("data[0].id", equalTo(1))
                .body("data[0].name", equalTo("cerulean"))
                .body("data[0].year", equalTo(2000))
                .body("data[0].color", equalTo("#98B2D1"))
                .body("data[0].pantone_value", equalTo("15-4020"));
    }

    @Step("Получение одного ресурса")
    public void getSingleResource(String baseURL, int id){
        given()
                .baseUri(baseURL)
                .when()
                .get("/api/unknown" + id)
                .then()
                .statusCode(200)
                .body("data[0].id", equalTo(1))
                .body("data[0].name", equalTo("cerulean"))
                .body("data[0].year", equalTo(2000))
                .body("data[0].color", equalTo("#98B2D1"))
                .body("data[0].pantone_value", equalTo("15-4020"));
    }

    @Step("Ресурс не найден")
    public void singleResourceNotFound(String baseURL, int id){
        given()
                .baseUri(baseURL)
                .when()
                .get("/api/unknown/" + id)
                .then()
                .statusCode(404);
    }

    @Step("Создание пользователя")
    public void createUser(String baseURL, String createUserJSON){
        given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(createUserJSON)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Step("Обновление пользователя (update)")
    public void updateUser(String baseURL, String updateUserJSON, int id){
        given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(updateUserJSON)
                .when()
                .put("/api/users/" + id)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));
    }

    @Step("Обновление пользователя (patch)")
    public void patchUser(String baseURL, String updateUserJSON, int id){
        given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(updateUserJSON)
                .when()
                .patch("/api/users/" + id)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));
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
