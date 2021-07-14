package com.brq.santander.cadeiasocietaria.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.brq.santander.cadeiasocietaria.model.CorporateChain;

@Repository
public interface CorporateChainRepository extends CassandraRepository<CorporateChain, String> {
	
	@AllowFiltering
	List<CorporateChain> findByPenumpri(String penumpri);

}
