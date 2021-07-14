package com.brq.santander.cadeiasocietaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brq.santander.cadeiasocietaria.dto.KafkaDTO;
import com.brq.santander.cadeiasocietaria.service.KafkaService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	
	private KafkaService kafkaService;
	
	
	@Autowired
    public KafkaController(KafkaService kafkaService) {
		super();
		this.kafkaService = kafkaService;
	}



	@PostMapping
    public ResponseEntity<String> enviarMensagem(@RequestBody KafkaDTO value){
		kafkaService.sendMessage(value); 
        return ResponseEntity.ok().body("Mensagem enviada com sucesso: " + value.toString());
    }

}
