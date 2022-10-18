package petstore;

import models.lombok.BodyLombokModel;
import models.lombok.ResponseLombokModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class petStoreTests extends TestBase {

    @Test
    @DisplayName("Get pet information by id")
    void getPetByIdTests() {

        BodyLombokModel bodyLombokModel = new BodyLombokModel();
        bodyLombokModel.setId("10");
        bodyLombokModel.setName("doggie");

        ResponseLombokModel response = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyLombokModel)
                .when()
                .get("/" + bodyLombokModel.getId())
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .as(ResponseLombokModel.class);

        assertThat(response.getId()).isEqualTo("10");
        assertThat(response.getName()).isEqualTo("doggie");
    }
}
