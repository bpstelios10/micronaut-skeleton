package framework.samples.micronaut.functional.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class PrivateEndpointStepDefinition {

    private Response response;

    @When("the client makes the call")
    public void theClientMakesTheCall() {
        response = RestAssured.given().baseUri("http://localhost:8080/").basePath("private/status").get();

    }

    @Then("a response with code {int} is returned")
    public void aResponseWithCodeIsReturned(int statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @Then("the response body is {string}")
    public void theResponseBodyIsOK(String body) {
        assertThat(response.body().print()).isEqualTo(body);
    }
}
