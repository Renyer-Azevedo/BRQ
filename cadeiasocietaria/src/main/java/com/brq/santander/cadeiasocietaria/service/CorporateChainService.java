package com.brq.santander.cadeiasocietaria.service;

import java.util.List;

import com.brq.santander.cadeiasocietaria.dto.CorporateChainPageableResponseDTO;
import com.brq.santander.cadeiasocietaria.model.CorporateChain;

public interface CorporateChainService {

	List<CorporateChain> listarTodos();
	
	CorporateChainPageableResponseDTO buscarCorporateChainPorPenumpri(String penumpri, Integer offset, Integer limit, String sortBy) throws Exception;
	
}
