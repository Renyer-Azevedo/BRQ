package com.brq.santander.cadeiasocietaria.service;

import java.util.List;

import com.brq.santander.cadeiasocietaria.dto.CompletenessCorporateChainDTO;

public interface CompletenessCorporateChainService {
	
	List<CompletenessCorporateChainDTO> buscarCompletenessCorporateChainPorPenumpri(String penumpri);

}
