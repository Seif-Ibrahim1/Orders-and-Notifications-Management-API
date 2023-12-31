package com.example.ordersmanagement.notification;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NotifierSerializer extends JsonSerializer<Notifier> {
    @Override
    public void serialize(Notifier notifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", notifier.getClass().getSimpleName());

        Notifier wrappee = notifier.getWrappee();
        if (wrappee != null) {
            jsonGenerator.writeObjectField("wrappee", wrappee); // Recursively serialize the wrappee
        }

        jsonGenerator.writeEndObject();
    }
}

