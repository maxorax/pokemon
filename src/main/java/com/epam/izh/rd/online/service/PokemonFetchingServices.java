package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.JsonObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonFetchingServices implements PokemonFetchingService {
    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        Pokemon pokemon = new Pokemon();
            ObjectMapper mapper = new JsonObjectMapper().getObjectMapper();

        JsonNode root = null;
        try {
            root = mapper.readTree(getJsonText(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pokemon.setPokemonId(root.path("id").asInt());
            pokemon.setPokemonName(root.path("name").asText());
            JsonNode stats = root.path("stats");
            pokemon.setHp(Short.parseShort(stats.get(0).path("base_stat").asText()));
            pokemon.setAttack(Short.parseShort(stats.get(1).path("base_stat").asText()));
            pokemon.setDefense(Short.parseShort(stats.get(2).path("base_stat").asText()));

        System.out.println(pokemon.toString());
        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        ByteArrayOutputStream baos = null;
        ObjectMapper mapper = new JsonObjectMapper().getObjectMapper();
        try {
            JsonNode root = mapper.readTree(getJsonText(name));
            JsonNode sprites = root.path("sprites");
            BufferedImage image = ImageIO.read(new URL(sprites.path("front_default").asText()));
            baos = new ByteArrayOutputStream();
            ImageIO.write(image,"png",baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    public String getJsonText(String name) throws IOException {
        HttpURLConnection connection = null;
            connection = (HttpURLConnection) new URL("https://pokeapi.co/api/v2/pokemon/" + name + "/").openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                connection.disconnect();
                return result.toString();
            } else {
                connection.disconnect();
                throw new IllegalArgumentException("Такого покемона не сущетсвует!");
            }

    }
}
