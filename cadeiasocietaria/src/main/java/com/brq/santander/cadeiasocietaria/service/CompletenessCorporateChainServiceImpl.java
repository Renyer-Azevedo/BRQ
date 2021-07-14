package com.brq.santander.cadeiasocietaria.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.brq.santander.cadeiasocietaria.deserializer.KafkaDeserializer;
import com.brq.santander.cadeiasocietaria.dto.CompletenessCorporateChainDTO;
import com.brq.santander.cadeiasocietaria.dto.KafkaDTO;
import com.brq.santander.cadeiasocietaria.exception.BusinessRuleException;
import com.brq.santander.cadeiasocietaria.model.CoporateChainOnline;
import com.brq.santander.cadeiasocietaria.repository.CorporateChainOnlineRepository;

@Service
public class CompletenessCorporateChainServiceImpl implements CompletenessCorporateChainService{

	@Value("${topic.producer}")
	private String topicProducer;

	@Value("${topic.consumer}")
	private String topicConsumer;
	
	@Value("${bootstrap-servers}")
	private String bootstrapServer;
	
	@Autowired
	public CompletenessCorporateChainServiceImpl(CorporateChainOnlineRepository corporateChainOnlineRepository) {
		super();
		this.corporateChainOnlineRepository = corporateChainOnlineRepository;
	}

	@Value("${group}")
	private String group;
	
	private CorporateChainOnlineRepository corporateChainOnlineRepository;

	@Override
	public List<CompletenessCorporateChainDTO> buscarCompletenessCorporateChainPorPenumpri(String penumpri) {

		List<CompletenessCorporateChainDTO> listaCompletenessCorporateChainDTO = new ArrayList<>();
		
		validarPenumpri(penumpri);
		
		CoporateChainOnline corporateChainOnline = buscaCompletenessCorporateChainNoCache(penumpri);
		
		if (corporateChainOnline != null) {
			listaCompletenessCorporateChainDTO.add(preencheCorporateChainDTOCache(corporateChainOnline));
		}
		else {
			listaCompletenessCorporateChainDTO.add(realizaCalculoKafka(penumpri));
		}

		return listaCompletenessCorporateChainDTO;
	}

	private void enviaMensagemKafka(String key, String valor) {

		try(KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(configuracaoKafkaProducer())) {

			ProducerRecord<String, String> kafkaRecord = new ProducerRecord<>(topicProducer,key,valor);

			kafkaProducer.send(kafkaRecord);

		}

	}

	private CompletenessCorporateChainDTO lerMensagemKafka(String key) {

		try(KafkaConsumer<String, KafkaDTO> kafkaConsumer = new KafkaConsumer<>(configuracaoKafkaConsumer())) {

			kafkaConsumer.subscribe(Arrays.asList(topicConsumer));

			ConsumerRecords<String, KafkaDTO> kafkaRecords = kafkaConsumer.poll(Duration.ofMinutes(1));

			if(!kafkaRecords.isEmpty()) {

				for (ConsumerRecord<String, KafkaDTO> kafkaRecord : kafkaRecords) {

					if (key.equalsIgnoreCase(kafkaRecord.key())) {

						return new CompletenessCorporateChainDTO(kafkaRecord.value().getPenumper(), kafkaRecord.value().getIndicador_completude(), kafkaRecord.value().getPerc_completude().toString());

					}

				}

			}

		}

		throw new BusinessRuleException("Não foi possível realizar a requisição, timeout.", null, HttpStatus.BAD_REQUEST);



	}

	private Properties configuracaoKafkaProducer() {

		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		return properties;

	}

	private Properties configuracaoKafkaConsumer() {

		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, group);

		return properties;

	}
	
	private void validarPenumpri(String penumpri) {
		
		if (penumpri == null || penumpri.isEmpty()) {
			
			throw new BusinessRuleException("Campo não pode está vazio.", null, HttpStatus.NOT_FOUND);
			
		}
		
		
	}
	
	private CoporateChainOnline buscaCompletenessCorporateChainNoCache(String pernumper) {
		return corporateChainOnlineRepository.findByPenumper(pernumper);
	}
	
	private CompletenessCorporateChainDTO preencheCorporateChainDTOCache(CoporateChainOnline corporateChainOnline) {
		
		return new CompletenessCorporateChainDTO(corporateChainOnline.getPenumper(), corporateChainOnline.getIndicador_completude(), corporateChainOnline.getPerc_completude().toString());
	}
	
	private CompletenessCorporateChainDTO realizaCalculoKafka(String penumpri) {

		enviaMensagemKafka(penumpri, penumpri);
		
		return lerMensagemKafka(penumpri);
		
	}


}
