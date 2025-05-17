package com.example.api;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.Assert;
import java.util.List;
import java.util.Map;

public class InventoryStepDefs {
    private Response response;
    private String baseUrl = "http://localhost:3100";
    private String lastPostBody = null;

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = RestAssured.given().get(baseUrl + endpoint);
    }

    @When("I send a POST request to {string} with body")
    public void i_send_a_post_request_to_with_body(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> body = rows.get(0);
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(baseUrl + endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer code) {
        Assert.assertEquals(code.intValue(), response.getStatusCode());
    }

    @Then("the response should contain at least {int} items")
    public void the_response_should_contain_at_least_items(Integer minCount) {
        System.out.println("DEBUG: " + response.getBody().asString());
        List<?> items = response.jsonPath().getList("data");
        if (items == null) {
            throw new AssertionError("Response does not contain a 'data' array");
        }
        Assert.assertTrue(items.size() >= minCount);
    }

    @Then("each item should have fields {string}, {string}, {string}, {string}")
    public void each_item_should_have_fields(String id, String name, String price, String image) {
        System.out.println("DEBUG: " + response.getBody().asString());
        List<Map<String, Object>> items = response.jsonPath().getList("data");
        if (items == null) {
            throw new AssertionError("Response does not contain a 'data' array");
        }
        for (Map<String, Object> item : items) {
            Assert.assertTrue(item.containsKey(id));
            Assert.assertTrue(item.containsKey(name));
            Assert.assertTrue(item.containsKey(price));
            Assert.assertTrue(item.containsKey(image));
        }
    }

    @Then("the response should contain an item with id {string}, name {string}, price {string}, and image {string}")
    public void the_response_should_contain_an_item_with_id_name_price_and_image(String id, String name, String price, String image) {
        System.out.println("DEBUG: " + response.getBody().asString());
        List<Map<String, Object>> items = response.jsonPath().getList("data");
        if (items == null) {
            throw new AssertionError("Response does not contain a 'data' array");
        }
        boolean found = false;
        for (Map<String, Object> item : items) {
            Assert.assertNotNull("id is missing in the response", item.get("id"));
            Assert.assertNotNull("name is missing in the response", item.get("name"));
            Assert.assertNotNull("price is missing in the response", item.get("price"));
            Assert.assertNotNull("image is missing in the response", item.get("image"));
            if (id.equals(item.get("id").toString()) &&
                name.equals(item.get("name")) &&
                price.equals(item.get("price")) &&
                image.equals(item.get("image"))) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("Expected item not found", found);
    }

    @Then("the response should contain the message {string}")
    public void the_response_should_contain_the_message(String message) {
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains(message));
    }
} 