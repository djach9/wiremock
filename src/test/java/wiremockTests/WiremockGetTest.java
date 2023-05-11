package wiremockTests;

import org.apache.http.client.fluent.Request;
import org.junit.BeforeClass;
import org.junit.Test;
import wiremockBase.WiremockBaseTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class WiremockGetTest extends WiremockBaseTest {

    @BeforeClass
    public static void setUp() {
        configureFor("localhost", PORT);
        stubFor(get(urlEqualTo("/api/get"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello, World! This is a GET request")));
    }


    @Test
    public void testGet() throws Exception {
        String response = Request.Get("http://localhost:" + PORT + "/api/get")
                .execute()
                .returnContent()
                .asString();
        System.out.println("Response: " + response); // Wyświetlenie odpowiedzi w konsoli
        assertEquals("Hello, World! This is a GET request", response);
    }

}
