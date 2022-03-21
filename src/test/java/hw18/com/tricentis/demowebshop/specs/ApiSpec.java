package hw18.com.tricentis.demowebshop.specs;

import hw18.com.tricentis.demowebshop.config.ConfigHelper;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static hw18.com.tricentis.demowebshop.filters.LogFilter.filters;
import static io.restassured.RestAssured.given;

public class ApiSpec {

    private final RequestSpecification request = given()
            .baseUri(ConfigHelper.getBaseUrl())
            .contentType(ContentType.URLENC)
            .filters(filters().withCustomTemplates(), new RequestLoggingFilter(), new ResponseLoggingFilter())
            .when();

    public static ApiSpec spec() {
        return new ApiSpec();
    }

    public RequestSpecification request(String path) {
        return request.basePath(path);
    }
}
