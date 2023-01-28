package test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;
import org.apache.commons.codec.binary.Base64;

import static io.restassured.RestAssured.given;

public class JiraIssueTypes implements RequestCapability {
    public static void main(String[] args) {
        String baseUri = "https://testautoapi.atlassian.net/";
        String path = "/rest/api/3/project/RAT";

        String email = "sangle.qc.98@gmail.com";
        String apiToken = "FcMJdX8Z9CT8ezllQbH1014E";
        String cred = email.concat(":").concat(apiToken);

        byte[] encodedCred = Base64.encodeBase64(cred.getBytes());
        String encodedCredStr = new String(encodedCred);

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header(new Header("Authorization", "Basic" + " " + encodedCredStr));

        Response response = request.get(path);
        response.prettyPrint();
    }
}
