package com.brq.santander.cadeiasocietaria.service;

import java.util.List;

import com.brq.santander.cadeiasocietaria.dto.CorporateChainDto;
import com.brq.santander.cadeiasocietaria.model.CorporateChain;

public interface CorporateChainService {

	List<CorporateChain> listarTodos();
	
	List<CorporateChainDto> buscarCorporateChainPorPenumpri(String penumpri);
	
}
