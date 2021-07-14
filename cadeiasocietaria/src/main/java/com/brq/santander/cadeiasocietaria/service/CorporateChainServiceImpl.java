package com.brq.santander.cadeiasocietaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.brq.santander.cadeiasocietaria.dto.CorporateChainDto;
import com.brq.santander.cadeiasocietaria.model.CorporateChain;
import com.brq.santander.cadeiasocietaria.repository.CorporateChainRepository;

@Service
public class CorporateChainServiceImpl implements CorporateChainService{
	
	private CorporateChainRepository corporateChainRepository;

	@Autowired
	public CorporateChainServiceImpl(CorporateChainRepository corporateChainRepository) {
		super();
		this.corporateChainRepository = corporateChainRepository;
	}

	@Override
	public List<CorporateChain> listarTodos() {
		return corporateChainRepository.findAll();
	}

	@Override
	public List<CorporateChainDto> buscarCorporateChainPorPenumpri(String penumpri) {
		
		List<CorporateChain> corporateChainList = corporateChainRepository.findByPenumpri(penumpri);
		
		if (!CollectionUtils.isEmpty(corporateChainList)) {
			
			List<CorporateChainDto> corporateChainDtoList = new ArrayList<>();
			
			corporateChainList.stream().forEach(corporateChain -> corporateChainDtoList.add(new CorporateChainDto(corporateChain.getPecodrel(), corporateChain.getPetipdoc(), corporateChain.getPenumero(), corporateChain.getPenomper(), corporateChain.getPenomfan(), corporateChain.getPenumrel(), corporateChain.getPenumero(), corporateChain.getPeusumod(), corporateChain.getOrigem(), corporateChain.getPenivel().intValue())));
			
			return corporateChainDtoList;
			
		}
		
		return new ArrayList<>();
	}
	

}
