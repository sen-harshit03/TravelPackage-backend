package com.orgname.travelbooking.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Serializer<T> {

    private final ObjectMapper mapper;
    public Serializer() {
        mapper = new ObjectMapper();
    }


    public T convertStringToObject(final String stringValue, final Class<T> classType) {
        try {
            return mapper.readValue(stringValue, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
