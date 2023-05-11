package wiremockTests;

import org.apache.http.client.fluent.Request;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import wiremockBase.WiremockBaseTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class WiremockPatchTest extends WiremockBaseTest {

    @BeforeClass
    public static void setUp() {
        configureFor("localhost", PORT);
        stubFor(patch(urlEqualTo("/api/patch"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello, World! This is a PATCH request")));
    }

    @Test
    public void testPatch() throws Exception {
        String response = Request.Patch("http://localhost:" + PORT + "/api/patch")
                .execute()
                .returnContent()
                .asString();
        System.out.println("Response: " + response); // Wyświetlenie odpowiedzi w konsoli
        assertEquals("Hello, World! This is a PATCH request", response);
    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }
}
