package com.epam.izh.rd.online.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;


import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;

class PokemonFetchingServicesTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        stubFor(get(urlEqualTo("/pokemon/pikachu/"))
                .willReturn(aResponse().withStatus(200)));
    }

    @Test
    void fetchByName() {
        assertEquals("pikachu", new PokemonFetchingServices().fetchByName("pikachu").getPokemonName());
    }

    @Test
    void getJsonText() throws IOException {
        assertNotNull(new PokemonFetchingServices().getJsonText("pikachu"));
    }

    @Test
    void getJsonTextException() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new PokemonFetchingServices().getJsonText("max");
        });
        assertNotNull(throwable.getMessage());
    }

    @Test
    public void testStatusCodePositive() {
        given().
                when().
                get("http://localhost:8080/pokemon/pikachu/").
                then().
                assertThat().statusCode(200);
        tearDown();
    }

    @Test
    void getPokemonImage() {
        byte[] bytes =   new PokemonFetchingServices().getPokemonImage("slowpoke");
        assertNotNull(bytes);
    }


    public static void tearDown(){
        wireMockServer.stop();
    }
}