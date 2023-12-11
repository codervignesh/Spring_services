package com.example.inventory.config;

import com.example.inventory.model.entity.Category;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CategorySerializer extends JsonSerializer<Category> {

    @Override
    public void serialize(Category category, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", category.getId());
        jsonGenerator.writeStringField("name", category.getName());
        if (category.getProduct() != null && !category.getProduct().isEmpty()) {
            jsonGenerator.writeObjectField("products", category.getProduct());
        }
        jsonGenerator.writeEndObject();
    }
}
