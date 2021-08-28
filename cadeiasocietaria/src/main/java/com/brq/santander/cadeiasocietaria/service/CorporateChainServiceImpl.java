package com.brq.santander.cadeiasocietaria.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
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

	@SuppressWarnings("unchecked")
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
	          
	          PageableTemplateDTO pageableTemplateDTO = criarPaginacao(corporateChainResponseDTO,offset,limit);
	          ordernar(CorporateChainResponseDTO.class, corporateChainResponseDTO, sortBy);
			  List<CorporateChainResponseDTO> corporateChainResponseDTOSlice = (List<CorporateChainResponseDTO>) sliceLista(corporateChainResponseDTO, offset, limit);
	          return CorporateChainPageableResponseDTO.builder().pageable(pageableTemplateDTO).content(new ArrayList<>(corporateChainResponseDTOSlice)).build();
	        }
	    } 
		
		return null;
	}
	
	private PageableTemplateDTO criarPaginacao(List<?> lista, Integer offset, Integer limit) {
		
		PageableTemplateDTO pageableTemplatePageable = new PageableTemplateDTO();
		
		pageableTemplatePageable.setLimit(limit);
		pageableTemplatePageable.setOffset(offset);
		pageableTemplatePageable.setPageElements(criarElementosPorPagina(lista.size(),offset,limit));
		pageableTemplatePageable.setTotalElements((!CollectionUtils.isEmpty(lista)) ? lista.size() : 0);
		pageableTemplatePageable.setTotalPages(criarTotalPaginas((!CollectionUtils.isEmpty(lista)) ? lista.size() : 0, limit));
		pageableTemplatePageable.setPageNumber(criarNumeroPagina(offset, limit));
		
		return pageableTemplatePageable;
	}
	
	private Integer criarElementosPorPagina(Integer totalElementos, Integer offset, Integer limit) {
		
		Integer elementosPagina = 0;
		
		if ((offset + limit) > totalElementos) {
			
			elementosPagina = ((offset + limit) - totalElementos) - limit;
			if (elementosPagina <= 0) {
				return elementosPagina * (-1);
			}
			else {
				return 0;
			}
		}
		
		return limit;
		
	}
	
	private Integer criarTotalPaginas(Integer totalElementos, Integer limit) {
		
		if (limit >= totalElementos)
			return 1;
		
		return Math.incrementExact(Math.floorDiv(totalElementos, limit)); 
		
	}
	
	private Integer criarNumeroPagina(Integer offset, Integer limit) {
		
		if (offset < limit)
			return 1;
		
		return Math.incrementExact(Math.floorDiv(offset, limit));
		
	}
	
	private List<?> sliceLista(List<?> lista,Integer offset, Integer limit) {
		
		Integer totalElementos = criarElementosPorPagina(lista.size(),offset,limit);
		Integer ultimoElemento = totalElementos + offset;
		Integer primeiroElemento = criarNumeroPagina(offset, limit) * offset; 
		
		if (totalElementos == 0 || offset >= ultimoElemento)
			return new ArrayList<>();
		
		if (primeiroElemento >= ultimoElemento) 
			primeiroElemento = offset;
		
		if (ultimoElemento == lista.size() && offset == 0)
			return lista;
		
		if (ultimoElemento > lista.size())
			ultimoElemento--;
		
		return lista.subList(primeiroElemento, ultimoElemento);
		
	}
	
	private void ordernar(final Class<?> cls, final List<?> lista, final String sortBy) throws Exception {
		
		String sortMapeado = mapearSortFiltroParaObjetoComparator(sortBy);
		
		String[] sorteMapeadoArray = sortMapeado.split(",");
		
    	Collections.sort(lista, (o1, o2) -> {
			try {
				Field f = cls.getDeclaredField(sorteMapeadoArray[0]);
				f.setAccessible(true);
				if (f.get(o1) != null && f.get(o2) != null) {
					int comparador = f.get(o1).toString().compareTo(f.get(o2).toString());
					if (comparador != 0)
						return comparador;
					if (sorteMapeadoArray.length == 2) {
						f = cls.getDeclaredField(sorteMapeadoArray[1]);
						f.setAccessible(true);
						return f.get(o1).toString().compareTo(f.get(o2).toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0; 
		});

	}
	
	private String mapearSortFiltroParaObjetoComparator(String sortBy) {
		
		StringBuilder atributosObjeto = new StringBuilder();
		final String ordenacaoPadrao = "nivel";
		
		for (String sort : sortBy.split(",")) {
		
			switch (sort.toLowerCase().trim()) {
			case "tipo_relac":
				atributosObjeto.append("tipoRelacionamento").append(",");
				break;
			case "tipo_docto":
				atributosObjeto.append("tipoDocumento").append(",");
				break;
			case "num_doc":
				atributosObjeto.append("numeroDocumento").append(",");
				break;
			case "nome_razao_social":
				atributosObjeto.append("razaoSocial").append(",");
				break;
			case "representante":
				atributosObjeto.append("representante").append(",");
				break;
			case "beneficiario":
				atributosObjeto.append("beneficiario").append(",");
				break;
			case "cargo_empresa":
				atributosObjeto.append("cargoEmpresa").append(",");
				break;
			case "cargo_conselho":
				atributosObjeto.append("cargoConselho").append(",");
				break;
			case "tipo_partic":
				atributosObjeto.append("tipoParticipacao").append(",");
				break;
			case "nivel":
				atributosObjeto.append("nivel").append(",");
				break;
			case "per_partic":
				atributosObjeto.append("percentualParticipacao").append(",");
				break;
			case "origem":
				atributosObjeto.append("origem").append(",");
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
