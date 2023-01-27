package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PUTMethod {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com/";

        // Form up request object and header
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(new Header("Content-type", "application/json; charset=UTF-8"));

        // Construct body
        PostBody postBody1 = new PostBody(1, 1, "New Title 1", "New Body 1");
        PostBody postBody2 = new PostBody(1, 1, "New Title 2", "New Body 2");
        PostBody postBody3 = new PostBody(1, 1, "New Title 3", "New Body 3");

        // Send PUT request
        final int TARGET_POST_NUM = 1;
        Gson gson = new Gson();

        List<PostBody> postBodies = Arrays.asList(postBody1, postBody2, postBody3);
        for (PostBody postBody : postBodies) {
            String postBodyStr = gson.toJson(postBody);
            Response response = request.body(postBodyStr).put("/posts/".concat(String.valueOf(TARGET_POST_NUM)));
            response.prettyPrint();
            response.then().body("userId", equalTo(postBody.getUserId()));
            response.then().body("id", equalTo(postBody.getId()));
            response.then().body("title", equalTo(postBody.getTitle()));
            response.then().body("body", equalTo(postBody.getBody()));
        }
    }
}
