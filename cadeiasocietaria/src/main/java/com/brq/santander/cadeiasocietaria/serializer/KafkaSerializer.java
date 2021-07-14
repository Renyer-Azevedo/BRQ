package com.brq.santander.cadeiasocietaria.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.brq.santander.cadeiasocietaria.dto.KafkaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaSerializer implements Serializer<KafkaDTO>{

	@Override
	public byte[] serialize(String topic, KafkaDTO data) {

		try {
			return new ObjectMapper().writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
