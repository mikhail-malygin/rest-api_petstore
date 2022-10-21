package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class PetSpecs {

    public static RequestSpecification getPetRequestSpec = with()
            .log().uri()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification getPetResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("id", notNullValue())
            .expectBody("name", notNullValue())
            .build();

    public static RequestSpecification addPetRequestSpec = with()
            .log().uri()
            .contentType(JSON)
            .log().body();

    public static ResponseSpecification addPetResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("id", notNullValue())
            .expectBody("name", notNullValue())
            .expectBody("status", notNullValue())
            .build();
}
