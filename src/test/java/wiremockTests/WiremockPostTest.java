package wiremockTests;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import wiremockBase.WiremockBaseTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class WiremockPostTest extends WiremockBaseTest {

    @BeforeClass
    public static void setUp() {
        configureFor("localhost", PORT);
        stubFor(post(urlEqualTo("/api/post"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("This is a POST request")));
    }

    @Test
    public void testPost() throws Exception {
        String response = Request.Post("http://localhost:" + PORT + "/api/post")
                .bodyString("Request body", ContentType.TEXT_PLAIN)
                .execute()
                .returnContent()
                .asString();
        System.out.println("Response: " + response);
        assertEquals("This is a POST request", response);
    }
}
