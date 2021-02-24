package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static constants.Endpoints.*;
import static io.restassured.RestAssured.given;

public class GetSteps {

    private String endpoint;
    private static Response response;

    @Given("The user endpoint exists")
    public void preconditions() {
        RestAssured.baseURI = BASE_API_TEST_URL;
        endpoint = GET_USER;
    }

    @When("I send a valid GET request")
    public void sendGetRequest() {
        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }

    @Then("GET response status code should be {int}")
    public void checkResponseStatusCode(int code) {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @Then("Response time should be less then {int}")
    public void responseTimeShouldBeLessThen(int time) {
        Assert.assertTrue("ERROR! Response time is more than checked!", response.getTime() <= time);
    }

    @Then("Response contains the specified user {string}")
    public void responseContainsTheSpecifiedUser(String name) {
        String username = response.jsonPath().getString("name");
        Assert.assertEquals("ERROR! Response does not contains the specified user!", name, username);
    }

    @Then("Response contains the specified header {string}")
    public void responseContainsTheSpecifiedHeader(String header) {
        String string = response.getHeaders().getValue(header);
        Assert.assertTrue("ERROR! Response does not contains the specified user!", response.getHeaders().hasHeaderWithName(header));
    }
}