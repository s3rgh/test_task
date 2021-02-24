package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static constants.Endpoints.*;
import static io.restassured.RestAssured.given;

public class PostSteps {

    private String endpoint;
    private static Response response;


    @Given("The users endpoint exists")
    public void preconditions() {
        RestAssured.baseURI = BASE_API_TEST_URL;
        endpoint = POST_USER;
    }

    @When("I send a valid POST request")
    public void createUser() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .build();

        response = given().spec(requestSpecification).
                body("[\n" +
                        "    {\n" +
                        "        \"id\": 11,\n" +
                        "        \"name\": \"Sergey\",\n" +
                        "        \"username\": \"s3rgh\",\n" +
                        "        \"email\": \"Sincere@april.biz\",\n" +
                        "        \"address\": {\n" +
                        "            \"street\": \"Sun Light\",\n" +
                        "            \"suite\": \"Apt. 556\",\n" +
                        "            \"city\": \"Gwenborough\",\n" +
                        "            \"zipcode\": \"100-500\",\n" +
                        "            \"geo\": {\n" +
                        "                \"lat\": \"-37.3159\",\n" +
                        "                \"lng\": \"81.1496\"\n" +
                        "            }\n" +
                        "        },\n" +
                        "        \"phone\": \"8-800-555-3535\",\n" +
                        "        \"website\": \"hildegard.org\",\n" +
                        "        \"company\": {\n" +
                        "            \"name\": \"Romaguera-Crona\",\n" +
                        "            \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                        "            \"bs\": \"harness real-time e-markets\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "]")
                .post(endpoint);
    }

    @Then("POST response status code should be {int}")
    public void checkResponseStatusCode(int code) {
        Assert.assertEquals("ERROR! Response status code is wrong!", code, response.getStatusCode());
    }

    @Then("POST response body should exist")
    public void checkResponseBody() {
        Assert.assertNotEquals("ERROR! Response does not have body!", "", response.getBody().prettyPrint());
    }

    @Then("Response contains new user {string}")
    public void responseContainsTheSpecifiedUser(String name) {
        String username = response.jsonPath().getString("0.name");
        Assert.assertEquals("ERROR! Response does not contains the specified user!", name, username);
    }
}