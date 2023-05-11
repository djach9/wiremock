package wiremockTests;

import org.apache.http.client.fluent.Request;
import org.junit.BeforeClass;
import org.junit.Test;
import wiremockBase.WiremockBaseTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class WiremockPutTest extends WiremockBaseTest {

    @BeforeClass
    public static void setUp() {
        configureFor("localhost", PORT);
        stubFor(put(urlEqualTo("/api/put"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello, World! This is a PUT request")));
    }

    @Test
    public void testPut() throws Exception {
        String response = Request.Put("http://localhost:" + PORT + "/api/put")
                .execute()
                .returnContent()
                .asString();
        System.out.println("Response: " + response);
        assertEquals("Hello, World! This is a PUT request", response);
    }

}
