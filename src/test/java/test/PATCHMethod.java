package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BuildModelJSON;
import model.PostBody;
import model.RequestCapability;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PATCHMethod implements RequestCapability {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com/";
        // Form up request + Header
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);

        // Body
        PostBody postBody = new PostBody();
        postBody.setTitle("Update new title with Patch");
        String postBodyStr = BuildModelJSON.parseJSONString(postBody);
        final String TARGET_POST_ID = "1";

        // Send Patch request
        Response response = request.body(postBodyStr).patch("/posts/".concat(TARGET_POST_ID));
        response.prettyPrint();
        response.then().body("title", equalTo(postBody.getTitle()));
    }
}
