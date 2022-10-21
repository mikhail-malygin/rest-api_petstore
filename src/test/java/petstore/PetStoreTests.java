package petstore;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import models.lombok.BodyLombokModel;
import models.lombok.ResponseLombokModel;
import models.pojo.bodyModel.Category;
import models.pojo.bodyModel.PetBodyModel;
import models.pojo.bodyModel.Tags;
import models.pojo.responseModel.PetResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.PetSpecs.*;

public class PetStoreTests extends TestBase {

    @Test
    @DisplayName("Find pet by id")
    void getPetByIdTests() {

        BodyLombokModel bodyLombokModel = new BodyLombokModel();
        bodyLombokModel.setId("10");
        bodyLombokModel.setName("doggie");

        ResponseLombokModel responseGetPetById = given()
                .spec(getPetRequestSpec)
                .body(bodyLombokModel)
                .when()
                .get("/" + bodyLombokModel.getId())
                .then()
                .spec(getPetResponseSpec)
                .extract()
                .as(ResponseLombokModel.class);

        assertThat(responseGetPetById.getId()).isEqualTo("10");
        assertThat(responseGetPetById.getName()).isEqualTo("doggie");
    }

    @Test
    @DisplayName("Add a new pet to the store")
    void addNewPetTests() throws IOException {

        PetBodyModel petBodyModel = new PetBodyModel();
        petBodyModel.setId(151);
        petBodyModel.setName("Newton");
        petBodyModel.setStatus("available");

        Category category = new Category();
        category.setId(1);
        category.setName("Cats");
        petBodyModel.setCategory(category);

        Tags tags = new Tags();
        tags.setId(0);
        tags.setName("#tags");
        List<Tags> allTags = new ArrayList<>();
        allTags.add(tags);
        petBodyModel.setTags(allTags);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("https://ru.pinterest.com/pin/324681454399008279/");
        petBodyModel.setPhotoUrls(photoUrls);


        ObjectMapper objectMapper = new ObjectMapper();
        String petBodyModelJsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(petBodyModel);

        PetResponseModel responseAddPet = given()
                .spec(addPetRequestSpec)
                .body(petBodyModelJsonPayload)
                .when()
                .post()
                .then()
                .spec(addPetResponseSpec)
                .extract()
                .as(PetResponseModel.class);


        assertThat(responseAddPet.getId()).isEqualTo(151);
        assertThat(responseAddPet.getName()).isEqualTo("Newton");
        assertThat(responseAddPet.getStatus()).isEqualTo("available");
        assertThat(responseAddPet.getPhotoUrls().get(0)).isEqualTo("https://ru.pinterest.com/pin/324681454399008279/");


    }
}
