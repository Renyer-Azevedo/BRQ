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
import com.brq.santander.cadeiasocietaria.util.Paginacao;
import com.brq.santander.cadeiasocietaria.util.Utils;

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
	public CorporateChainPageableResponseDTO buscarCorporateChainPorPenumpri(String penumpri, Integer offset, Integer limit, String sortBy) throws Exception {
		
		if (penumpri != null && !penumpri.isEmpty()) {
			List<CorporateChain> corporateChainList = corporateChainRepository.findByPenumpri(penumpri);
	        if (!CollectionUtils.isEmpty(corporateChainList)) {
	            List<CorporateChainResponseDTO> corporateChainResponseDTO = new ArrayList<>();
	            corporateChainList.stream()
	              .forEach(corporateChainModel ->
	                        corporateChainResponseDTO.add(
	                            new CorporateChainResponseDTO(
	                                  Utils.valorSeguroString(corporateChainModel.getPecodrel()),
	                                  Utils.valorSeguroString(corporateChainModel.getPetipdoc()),
	                                  Utils.valorSeguroString(corporateChainModel.getRelnumero()),
	                                  Utils.valorSeguroString(corporateChainModel.getPenomper()),
	                                  Utils.valorSeguroBoolean(corporateChainModel.getPeindrep()),
	                                  Utils.valorSeguroBoolean(corporateChainModel.getFlagBf()),
	                                  Utils.valorSeguroString(corporateChainModel.getPecaradm()),
	                                  Utils.valorSeguroString(corporateChainModel.getPecarcon()),
	                                  Utils.valorSeguroString(corporateChainModel.getPetippar()),
	                                  Utils.valorSeguroDouble(corporateChainModel.getPenivel()).intValue(),
	                                  (int)Math.round(Utils.valorSeguroDouble(corporateChainModel.getPeporpar())),
	                                  Utils.valorSeguroString(corporateChainModel.getOrigem()))));
	            
	          Paginacao<CorporateChainResponseDTO> paginacao = new Paginacao<>(offset, limit, sortBy, corporateChainResponseDTO, CorporateChainResponseDTO.class);
	          
	          PageableTemplateDTO pageableTemplateDTO = new PageableTemplateDTO(limit, offset, paginacao.getNumeroPagina(), paginacao.getElementosPorPagina(), paginacao.getTotalPaginas(), paginacao.getTotalElementos());
	          
	          return CorporateChainPageableResponseDTO.builder().pageable(pageableTemplateDTO).content(paginacao.getConteudo()).build();
	        }
	    } 
		
		return null;
	}
	
	private String mapearSortFiltroParaObjetoComparator(String sortBy) {
		
		StringBuilder atributosObjeto = new StringBuilder();
		final String ordenacaoPadrao = "nivel";
		String[] sortArray = sortBy.split(",");
		
		for (String sortFitro : sortArray) {
			
			String sinal = "";
			String sort = "";
			
			if (sortFitro.startsWith("+") || sortFitro.startsWith("-")) {
				sort = sortFitro.substring(1);
				sinal = sortFitro.substring(0, 1);
			} else {
				sort = sortFitro;
				sinal = "+";
			}
				
			switch (sort.toLowerCase().trim()) {
			case "tipo_relac":
				atributosObjeto.append(sinal).append("tipoRelacionamento").append(",");
				break;
			case "tipo_docto":
				atributosObjeto.append(sinal).append("tipoDocumento").append(",");
				break;
			case "num_doc":
				atributosObjeto.append(sinal).append("numeroDocumento").append(",");
				break;
			case "nome_razao_social":
				atributosObjeto.append(sinal).append("razaoSocial").append(",");
				break;
			case "representante":
				atributosObjeto.append(sinal).append("representante").append(",");
				break;
			case "beneficiario":
				atributosObjeto.append(sinal).append("beneficiario").append(",");
				break;
			case "cargo_empresa":
				atributosObjeto.append(sinal).append("cargoEmpresa").append(",");
				break;
			case "cargo_conselho":
				atributosObjeto.append(sinal).append("cargoConselho").append(",");
				break;
			case "tipo_partic":
				atributosObjeto.append(sinal).append("tipoParticipacao").append(",");
				break;
			case "nivel":
				atributosObjeto.append(sinal).append("nivel").append(",");
				break;
			case "per_partic":
				atributosObjeto.append(sinal).append("percentualParticipacao").append(",");
				break;
			case "origem":
				atributosObjeto.append(sinal).append("origem").append(",");
				break;
			default:
				if (atributosObjeto.length() != 0 && atributosObjeto.indexOf(",") == -1)
					atributosObjeto.delete(0, atributosObjeto.length());
				break;
			}
			
		}
		
		return atributosObjeto.toString().endsWith(",") ? atributosObjeto.substring(0, atributosObjeto.length() - 1) : ordenacaoPadrao;
		
	}
	
	
}
