package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostGetPutDeleteSteps {

    private static final String BASE_URI = "https://reqres.in";

    RequestSpecification request;
    Response response;

    @Given("User sets the base API request {string}")
    public void userSetsTheBaseAPIRequest(String url) {
        RestAssured.baseURI = BASE_URI + url;
    }

    @Given("User sets the base API request")
    public void userSetsTheBaseAPIRequest() {
        RestAssured.baseURI = BASE_URI;
    }

    @When("User sends the API request to get all the list")
    public void userSendsTheAPIRequestToGetAllTheList() {
        response = request.get().then().log().all().extract().response();
    }

    @Then("User validates the response status is {int}")
    public void userValidatesTheResponseStatusIs(int code) {
        Assert.assertEquals(response.statusCode(), code, "Something was wrong!");
    }

    @And("User validates the response meta pagination {string} is {int}")
    public void userValidatesTheResponseMetaPaginationIs(String meta, int value) {
        Assert.assertEquals(response.jsonPath().getInt(meta), value, "Something was wrong! Please check '" + meta + "'");
    }

    @And("User validates the list body response equals with {int}")
    public void userValidatesTheListBodyResponseEqualsWith(int listData) {
        List<String> jsonResponse = response.jsonPath().getList("data");
        int list = jsonResponse.size();

        Assert.assertEquals(list, listData, "Something was wrong! Please check the list response");
    }

    @When("User create new data user with POST request {string} and request body {string}, {string}")
    public void userCreateNewDataUserWithPOSTRequestAndRequestBodyNameJob(String url, String name, String job) {

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name);
        requestParams.put("job", job);

        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body(requestParams.toJSONString()).post(BASE_URI + url);

        response = request.get().then().log().all().extract().response();
    }


    @And("User validates id is not null")
    public void userValidatesIdIsNotNull() {
        Assert.assertTrue(response.jsonPath().get("data.id") != null, "'data.id'" + " is empty");
    }

    @And("User validates id is null")
    public void userValidatesIdIsNull() {
        Assert.assertFalse(response.jsonPath().get("data.id") != null, "'data.id'" + " is empty");
    }

    @And("User validates the body response with array name {string}")
    public void userValidatesTheBodyResponseWithArrayName(String name) {
        Assert.assertEquals(response.body().jsonPath().get("data.name[0]"), name, "Something was wrong!");
    }

    @And("User validates the body response with array gender {string}")
    public void userValidatesTheBodyResponseWithArrayGender(String gender) {
        Assert.assertEquals(response.body().jsonPath().get("data.gender[0]"), gender, "Something was wrong!");
    }

    @And("User validates the body response with array email {string}")
    public void userValidatesTheBodyResponseWithArrayEmail(String email) {
        Assert.assertEquals(response.body().jsonPath().get("data.email[0]"), email, "Something was wrong!");
    }

    @And("User validates the body response with array status {string}")
    public void userValidatesTheBodyResponseWithArrayStatus(String status) {
        Assert.assertEquals(response.body().jsonPath().get("data.status[0]"), status, "Something was wrong!");
    }

    @And("User validates the body response with first name {string}")
    public void userValidatesTheBodyResponseWithFirstName(String name) {
        Assert.assertEquals(response.body().jsonPath().get("data.first_name"), name, "Something was wrong!");
    }

    @And("User validates the body response with last name {string}")
    public void userValidatesTheBodyResponseWithLastName(String name) {
        Assert.assertEquals(response.body().jsonPath().get("data.last_name"), name, "Something was wrong!");
    }

    @And("User validates the body response with job {string}")
    public void userValidatesTheBodyResponseWithJob(String gender) {
        Assert.assertEquals(response.body().jsonPath().get("data.gender"), gender, "Something was wrong!");
    }

    @And("User validates the body response with email {string}")
    public void userValidatesTheBodyResponseWithEmail(String email) {
        Assert.assertEquals(response.body().jsonPath().get("data.email"), email, "Something was wrong!");
    }

    @And("User validates the body response with status {string}")
    public void userValidatesTheBodyResponseWithStatus(String status) {
        Assert.assertEquals(response.body().jsonPath().get("data.status"), status, "Something was wrong!");
    }

    @Given("User gets new data user with GET request {string} with id {string}")
    public void userGetsNewDataUserWithGETRequestWithID(String url, String id) {
        request = given().contentType(ContentType.JSON);
        response = request.get(BASE_URI + url + id).then().log().all().extract().response();

    }

}
