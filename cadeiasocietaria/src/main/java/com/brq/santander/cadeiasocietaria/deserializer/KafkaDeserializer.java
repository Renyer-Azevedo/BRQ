package com.brq.santander.cadeiasocietaria.deserializer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.brq.santander.cadeiasocietaria.dto.KafkaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaDeserializer implements Deserializer<KafkaDTO>{

	@Override
	public KafkaDTO deserialize(String topic, byte[] data) {
		try {
			return new ObjectMapper().readValue(data, KafkaDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
