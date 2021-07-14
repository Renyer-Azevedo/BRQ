package com.brq.santander.cadeiasocietaria.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.brq.santander.cadeiasocietaria.model.CoporateChainOnline;

@Repository
public interface CorporateChainOnlineRepository extends CassandraRepository<CoporateChainOnline, String> {
	
	@AllowFiltering
	CoporateChainOnline findByPenumper(String penumpri);

}
