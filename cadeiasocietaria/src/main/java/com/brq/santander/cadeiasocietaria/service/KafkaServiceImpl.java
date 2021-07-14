package com.brq.santander.cadeiasocietaria.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.brq.santander.cadeiasocietaria.dto.KafkaDTO;

@Service
public class KafkaServiceImpl implements KafkaService{
	
    @Value("${topic.producer}")
    private String topicProducer;
	
    @Autowired
    private KafkaTemplate<String, KafkaDTO> kafkaTemplate;

	@Override
	public void sendMessage(KafkaDTO value) {
		
		kafkaTemplate.send(topicProducer, value.getPenumper(), value);
		
	}
	
   // @KafkaListener(topics = "${topic.consumer}", groupId = "group1")
    public void consume(ConsumerRecord<String, KafkaDTO> record){
        System.out.println("chave " + record.key() + " valor " + record.value().toString());
    }

}
