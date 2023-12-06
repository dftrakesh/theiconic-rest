package com.dft.theiconic.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    @SneakyThrows
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        ZonedDateTime zdtWithZoneOffset = ZonedDateTime.parse(jsonParser.getText().trim(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
        return zdtWithZoneOffset.toLocalDateTime();
    }
}