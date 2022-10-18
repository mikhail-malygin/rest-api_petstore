package petstore;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class petStoreTests extends TestBase {

    @Test
    void getPetById() {

        given()
                .log().uri()
                .when()
                .get("/10")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(10))
                .body("name", is("doggie"));
    }
}
