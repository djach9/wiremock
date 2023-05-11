package wiremockTests;

import org.apache.http.client.fluent.Request;
import org.junit.BeforeClass;
import org.junit.Test;
import wiremockBase.WiremockBaseTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class WiremockDeleteTest extends WiremockBaseTest {

    @BeforeClass
    public static void setUp() {
        configureFor("localhost", PORT);
        stubFor(delete(urlEqualTo("/api/delete"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello, World! This is a DELETE request")));
    }

    @Test
    public void testDelete() throws Exception {
        String response = Request.Delete("http://localhost:" + PORT + "/api/delete")
                .execute()
                .returnContent()
                .asString();
        System.out.println("Response: " + response); // Wyświetlenie odpowiedzi w konsoli
        assertEquals("Hello, World! This is a DELETE request", response);
    }

}
