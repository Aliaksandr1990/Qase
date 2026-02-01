package tests.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class QASESpec {

    public static final RequestSpecification REQ_SPEC = with()
            .baseUri("https://api.qase.io/v1")
            .filter(withCustomTemplate())
            .log().uri()
            .log().body()
            .contentType(JSON)
            .header("Token", "ae91de7e606be145c645a978f840b0158f90755e544f1f241e4f88eaef7e0edf");

    public static final ResponseSpecification responseWithStatusCode(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .expectStatusCode(statusCode)
                .build();

    }
}
