package com.example.inventory.config;


import com.example.inventory.model.entity.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductSerializer extends JsonSerializer<Product> {

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeStringField("name", product.getName());
        jsonGenerator.writeNumberField("price", product.getPrice());
        jsonGenerator.writeNumberField("quantity", product.getQuantity());
        if (product.getCategory() != null) {
            jsonGenerator.writeObjectField("category", product.getCategory());
        }
        jsonGenerator.writeEndObject();
    }
}
