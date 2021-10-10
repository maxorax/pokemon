package com.epam.izh.rd.online.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonObjectMapperTest {

    @Test
    void getObjectMapper(){
        assertEquals(new ObjectMapper().getClass(), new JsonObjectMapper().getObjectMapper().getClass() );
    }

}