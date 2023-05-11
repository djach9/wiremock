package wiremockBase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WiremockBaseTest {

    protected static WireMockServer wireMockServer;
    protected static final int PORT = 8888;

    @BeforeClass
    public static void setUp() {
        wireMockServer = new WireMockServer(wireMockConfig().port(PORT));
        wireMockServer.start();
    }


    @AfterAll
    public static void tearDown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }

    }
}