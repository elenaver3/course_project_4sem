package task5;

import common.BaseTest;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;


public class Test5 extends BaseTest {

    @BeforeClass
    public static void setUp() {
        BaseTest.setUp();
        requestSpecification.basePath("/users");
    }

    @Test
    public void test5Test1() {

        UsersList usersList = checkStatusCodeGet("?page=2", 200)
                .extract().as(UsersList.class);

        List<UserData> userData = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data", UserData.class);

        assertThat(usersList).isNotNull().extracting("page", "perPage", "total", "totalPages").containsExactly(2, 6, 12, 2);
        assertThat(userData).extracting(UserData::getId).isNotNull();
        assertThat(userData).extracting(UserData::getFirstName).contains("Tobias");
        assertThat(userData).extracting(UserData::getLastName).contains("Funke");

    }

    @Test
    public void test5Test2() {

        UserData userData = given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("data", UserData.class);

        assertThat(userData).extracting(UserData::getId).isEqualTo(2);
        assertThat(userData).extracting(UserData::getEmail).isEqualTo("janet.weaver@reqres.in");
        assertThat(userData).extracting(UserData::getFirstName).isEqualTo("Janet");
        assertThat(userData).extracting(UserData::getLastName).isEqualTo("Weaver");
        assertThat(userData).extracting(UserData::getAvatar).isEqualTo("https://reqres.in/img/faces/2-image.jpg");

    }

    @Test
    public void test5Test3() {

        given()
                .when()
                .get("https://reqres.in/api/users/22")
                .then()
                .statusCode(404)
                .body(equalTo("{}"));

    }

    @Test
    public void test5Test4() {

        List<ResourceData> resources = given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data", ResourceData.class);

        assertThat(resources).extracting(ResourceData::getId).isNotNull();
        assertThat(resources).extracting(ResourceData::getName).contains("cerulean");
        assertThat(resources).extracting(ResourceData::getYear).contains("2000");
        assertThat(resources).extracting(ResourceData::getColor).contains("#98B2D1");

    }

    @Test
    public void test5Test5() {
        ResourceData resourceData = given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("data", ResourceData.class);

        assertThat(resourceData).extracting(ResourceData::getId).isEqualTo(2);
        assertThat(resourceData).extracting(ResourceData::getName).isEqualTo("fuchsia rose");
        assertThat(resourceData).extracting(ResourceData::getYear).isEqualTo("2001");
        assertThat(resourceData).extracting(ResourceData::getColor).isEqualTo("#C74375");
        assertThat(resourceData).extracting(ResourceData::getPantone_value).isEqualTo("17-2031");
    }

    @Test
    public void test5Test6() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404)
                .body(equalTo("{}"));
    }

    @Test
    public void test5Test7() {
        UsersJobData usersJobData = UsersJobData.builder()
                    .name("morpheus")
                    .job("leader")
                    .build();

        UsersJobData usersJobData2 = given()
                .contentType(ContentType.JSON)
                .body(usersJobData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .extract().as(UsersJobData.class);

        assertThat(usersJobData2).isNotNull();
        assertThat(usersJobData2).extracting(UsersJobData::getName).isEqualTo(usersJobData2.getName());
        assertThat(usersJobData2).extracting(UsersJobData::getJob).isEqualTo(usersJobData2.getJob());
        assertThat(usersJobData2).extracting(UsersJobData::getId).isEqualTo(usersJobData2.getId());
        assertThat(usersJobData2).extracting(UsersJobData::getCreatedAt).isEqualTo(usersJobData2.getCreatedAt());
    }

    @Test
    public void test5Test8() {
        UsersJobData usersJobData = UsersJobData.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        UsersJobData usersJobData2 = given()
                .contentType(ContentType.JSON)
                .body(usersJobData)
                .when()
                .post("https://reqres.in/api/users/2")
                .then()
                .statusCode(201)
                .extract().as(UsersJobData.class);

        assertThat(usersJobData2).isNotNull();
        assertThat(usersJobData2).extracting(UsersJobData::getName).isEqualTo(usersJobData2.getName());
        assertThat(usersJobData2).extracting(UsersJobData::getJob).isEqualTo(usersJobData2.getJob());
        assertThat(usersJobData2).extracting(UsersJobData::getId).isEqualTo(usersJobData2.getId());
        assertThat(usersJobData2).extracting(UsersJobData::getCreatedAt).isEqualTo(usersJobData2.getCreatedAt());
    }

    @Test
    public void test5Test9() {
        UsersJobData usersJobData = UsersJobData.builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        UsersJobData usersJobData2 = given()
                .contentType(ContentType.JSON)
                .body(usersJobData)
                .when()
                .post("https://reqres.in/api/users/2")
                .then()
                .statusCode(201)
                .extract().as(UsersJobData.class);

        assertThat(usersJobData2).isNotNull();
        assertThat(usersJobData2).extracting(UsersJobData::getName).isEqualTo(usersJobData2.getName());
        assertThat(usersJobData2).extracting(UsersJobData::getJob).isEqualTo(usersJobData2.getJob());
        assertThat(usersJobData2).extracting(UsersJobData::getId).isEqualTo(usersJobData2.getId());
        assertThat(usersJobData2).extracting(UsersJobData::getCreatedAt).isEqualTo(usersJobData2.getCreatedAt());
    }

    @Test
    public void test5Test10() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .body(equalTo(""));
    }

    @Test
    public void test5Test11() {
        RegisterData registerData = RegisterData.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        RegisterData registerData2 = given()
                .contentType(ContentType.JSON)
                .body(registerData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .extract().as(RegisterData.class);

        assertThat(registerData2).isNotNull();
        assertThat(registerData2).extracting(RegisterData::getId).isEqualTo(4);
        assertThat(registerData2).extracting(RegisterData::getToken).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    public void test5Test12() {
        RegisterData registerData = RegisterData.builder()
                .email("test@gmail.ru")
                .build();

        RegisterData registerData2 = given()
                .contentType(ContentType.JSON)
                .body(registerData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .extract().as(RegisterData.class);

        assertThat(registerData2).isNotNull();
        assertThat(registerData2).extracting(RegisterData::getError).isEqualTo("Missing password");
    }

    @Test
    public void test5Test13() {
        RegisterData registerData = RegisterData.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        RegisterData registerData2 = given()
                .contentType(ContentType.JSON)
                .body(registerData)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .extract().as(RegisterData.class);

        assertThat(registerData2).isNotNull();
        assertThat(registerData2).extracting(RegisterData::getToken).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    public void test5Test14() {
        RegisterData registerData = RegisterData.builder()
                .email("test@gmail.ru")
                .build();

        RegisterData registerData2 = given()
                .contentType(ContentType.JSON)
                .body(registerData)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .extract().as(RegisterData.class);

        assertThat(registerData2).isNotNull();
        assertThat(registerData2).extracting(RegisterData::getError).isEqualTo("Missing password");
    }

    @Test
    public void test5Test15() {
        List<UserData> userData = given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
                .time(greaterThan(3000L)).and().time(lessThan(6000L))
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data", UserData.class);

        assertThat(userData).extracting(UserData::getId).contains(2);
        assertThat(userData).extracting(UserData::getFirstName).contains("Janet");
        assertThat(userData).extracting(UserData::getLastName).contains("Weaver");
    }

}
