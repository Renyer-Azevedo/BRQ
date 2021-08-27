package com.brq.santander.cadeiasocietaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.brq.santander.cadeiasocietaria.dto.CorporateChainPageableResponseDTO;
import com.brq.santander.cadeiasocietaria.dto.CorporateChainResponseDTO;
import com.brq.santander.cadeiasocietaria.dto.PageableTemplateDTO;
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
	public CorporateChainPageableResponseDTO buscarCorporateChainPorPenumpri(String penumpri, Integer offset, Integer limit, String sortBy) {
		
		if (penumpri != null && !penumpri.isEmpty()) {
			List<CorporateChain> corporateChainList = corporateChainRepository.findByPenumpri(penumpri);
	        if (!CollectionUtils.isEmpty(corporateChainList)) {
	            List<CorporateChainResponseDTO> corporateChainResponseDTO = new ArrayList<>();
	            corporateChainList.stream()
	              .forEach(corporateChainModel ->
	                        corporateChainResponseDTO.add(
	                            new CorporateChainResponseDTO(
	                                  corporateChainModel.getPecodrel(),
	                                  corporateChainModel.getPetipdoc(),
	                                  corporateChainModel.getRelnumero(),
	                                  corporateChainModel.getPenomper(),
	                                  corporateChainModel.getPeindrep(),
	                                  corporateChainModel.getFlagBf(),
	                                  corporateChainModel.getPecaradm(),
	                                  corporateChainModel.getPecarcon(),
	                                  corporateChainModel.getPetippar(),
	                                  corporateChainModel.getPenivel().intValue(),
	                                  Math.round(corporateChainModel.getPeporpar()),
	                                  corporateChainModel.getOrigem())));
	          PageableTemplateDTO pageableTemplatePageable = new PageableTemplateDTO(limit, offset, 1, 1, 1, corporateChainResponseDTO.size());
	          return CorporateChainPageableResponseDTO.builder().pageable(pageableTemplatePageable).content(new ArrayList<>(corporateChainResponseDTO)).build();
	        }
	    } 
		
		return null;
	}
	

}
