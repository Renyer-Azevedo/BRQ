package com.brq.santander.cadeiasocietaria.converter;

import java.time.Instant;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class InstantToStringConverter implements Converter<java.time.Instant, String>{

	@Override
	public String convert(Instant source) {
		return source != null ? source.toString() : "";
	}

}
