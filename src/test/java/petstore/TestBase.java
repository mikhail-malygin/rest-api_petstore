package petstore;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBase {

    @BeforeAll
    static void before() {
        baseURI = "https://petstore.swagger.io/v2/pet/";
    }
}
