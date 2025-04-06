package tests;

import io.qameta.allure.*;
import models.EntityRequest;
import org.testng.annotations.*;
import utils.APIEndpoints;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API Тесты для работы с сущностями")
@Feature("CRUD операций с сущностями")
public class EntityApiTest extends BaseTest {
    private Integer entityId;
    private EntityRequest testEntity;

    @BeforeClass
    public void setupTestData() {
        testEntity = EntityRequest.builder()
                .title("Test Entity " + System.currentTimeMillis())
                .verified(true)
                .importantNumbers(List.of(1, 2, 3))
                .addition(EntityRequest.Addition.builder()
                        .additionalInfo("Тестовые данные")
                        .additionalNumber(42)
                        .build())
                .build();
    }

    @Test
    @Story("Создание сущности")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка создания новой сущности")
    public void createEntityTest() {
        entityId = Integer.parseInt(given()
                .spec(requestSpec)
                .body(testEntity)
                .when()
                .post(APIEndpoints.CREATE_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body().
                asString());
    }

    @Test(dependsOnMethods = "createEntityTest", alwaysRun = true)
    @Story("Чтение сущности")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка получения данных сущности")
    public void getEntityTest() {
        createEntityTest();
              given()
                .spec(requestSpec)
                .pathParam("id", entityId)
                .when()
                .get(APIEndpoints.GET_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", equalTo(testEntity.getTitle()));;

    }

    @Test(dependsOnMethods = "createEntityTest")
    @Story("Обновление сущности")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка обновления данных сущности")
    public void updateEntityTest() {
        createEntityTest();
        EntityRequest updateData = testEntity.toBuilder()
                .title("Обновленный заголовок")
                .verified(false)
                .build();

        given()
                .spec(requestSpec)
                .pathParam("id", entityId)
                .body(updateData)
                .patch(APIEndpoints.UPDATE_ENDPOINT)
                .then()
                .statusCode(204);

        given()
                .spec(requestSpec)
                .pathParam("id", entityId)
                .get(APIEndpoints.GET_ENDPOINT)
                .then()
                .body("title", equalTo("Обновленный заголовок"))
                .body("verified",equalTo(false));
    }

    @Test(dependsOnMethods = {"createEntityTest", "getEntityTest"})
    @Story("Удаление сущности")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка удаления сущности")
    public void deleteEntityTest() {
        createEntityTest();
        given()
                .spec(requestSpec)
                .pathParam("id", entityId)
                .delete(APIEndpoints.DELETE_ENDPOINT)
                .then()
                .statusCode(204);


        given()
                .spec(requestSpec)
                .pathParam("id", entityId)
                .get(APIEndpoints.GET_ENDPOINT)
                .then()
                .statusCode(500);
    }

    @Test
    @Story("Список сущностей")
    @Severity(SeverityLevel.MINOR)
    @Description("Проверка получения списка сущностей")
    public void getAllEntitiesTest() {
        createEntityTest();
        Allure.addAttachment("Количество сущностей", String.valueOf(
         given()
                .spec(requestSpec)
                .queryParam("page", 1)
                .queryParam("limit", 10)
                 .when()
                .get(APIEndpoints.GET_ALL_ENDPOINT)
                 .then()
                 .assertThat()
                 .statusCode(200)
                 .extract()
                 .jsonPath()
                 .getList("entity.id")
                 .size()));
    }

    @AfterClass
    public void cleanupTestData() {
        if (entityId != null) {
            given()
                    .spec(requestSpec)
                    .pathParam("id", entityId)
                    .delete(APIEndpoints.DELETE_ENDPOINT);
        }
    }
}
