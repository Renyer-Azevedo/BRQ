package com.brq.santander.cadeiasocietaria.service;

import com.brq.santander.cadeiasocietaria.dto.KafkaDTO;

public interface KafkaService {
	
	void sendMessage(KafkaDTO value);

}
