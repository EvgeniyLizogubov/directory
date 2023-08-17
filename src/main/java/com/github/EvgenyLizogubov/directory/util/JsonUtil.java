package com.github.EvgenyLizogubov.directory.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@UtilityClass
public class JsonUtil {
    private static ObjectMapper mapper;
    
    public static void setMapper(ObjectMapper mapper) {
        JsonUtil.mapper = mapper;
    }
    
    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = mapper.readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }
    
    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }
    
    public static <T> String writeValue(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + object + "'", e);
        }
    }
    
    public static <T> String writeAdditionProps(T object, String addName, Object addValue) {
        return writeAdditionProps(object, Map.of(addName, addValue));
    }
    
    private static <T> String writeAdditionProps(T object, Map<String, Object> addProps) {
        Map<String, Object> map = mapper.convertValue(object, new TypeReference<>() {});
        map.putAll(addProps);
        return writeValue(map);
    }
}
