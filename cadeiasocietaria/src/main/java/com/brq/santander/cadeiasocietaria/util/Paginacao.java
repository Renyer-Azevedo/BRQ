package com.brq.santander.cadeiasocietaria.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.brq.santander.cadeiasocietaria.dto.PageableTemplateDTO;

 /**
  * <p>Classe responsável por tratar e realizar a paginação a partir de uma collection.
  * <p>Exemplo de paginação esperada baseado no objeto PageableTemplate (CDG):
  * <p>{
  * <p>"_pageable": {
  * <p>     "_limit": 0,
  * <p>     "_offset": 0,
  * <p>     "_pageNumber": 0,
  * <p>     "_pageElements": 0,
  * <p>     "_totalPages": 0,
  * <p>     "_totalElements": 0
  * <p> },
  * <p> "_content": [
  * <p>     {
  * <p>     	Corpo do objeto @param <T>, objeto que será o json de retorno
  * <p>     }
  * <p> ]
}
  * 
  * @author azevedorenyer
  *
  * @param <T> classe do objeto que retornará no content.
  */
public class Paginacao<T> {
	
	private Integer offset = 0;
	private Integer limit = 10;
	private String sortBy = "";
	private List<T> dados = new ArrayList<>();
	private Class<T> clazz = null;
	private static final String CRESCENTE = "+";
	private static final String DECRESCENTE = "-";
	private static final String DELIMITADOR = ",";
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Paginacao.class);
	
	/**
	 * 
	 * @param offset - ínicio dos dados a serem retornados.
	 * @param limit - limite de dados por página.
	 * @param sortBy - atributos da classe do objeto que retornará no content com sinal de ordenação crescente "+" ou descrescente "-" limitados por ",". Exemplo:<p>
	 * "+nivel,-razaoSocial" <p>
	 * @param dados - collection com os dados do tipo classe do objeto que retornará no content.
	 * @param clazz - a classe do objeto que retornará no content.
	 */
	public Paginacao(Integer offset, Integer limit, String sortBy, Collection<T> dados, Class<T> clazz) {
		super();
		this.offset = offset;
		this.limit = limit;
		this.sortBy = sortBy.trim();
		this.dados = extrairLista(dados);
		this.clazz = clazz;
	}

	public Integer getOffset() {
		return offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public String getSortBy() {
		return sortBy;
	}

	public List<T> getDados() {
		return new ArrayList<>(dados);
	}

	@SuppressWarnings("unchecked")
	private List<T> extrairLista(Collection<?> dados) {
		return (List<T>) dados;
	}
	
	/**
	 * 
	 * @return total de elementos a serem paginados
	 */
	public Integer getTotalElementos() {
		
		return !CollectionUtils.isEmpty(this.dados) ? this.dados.size() : 0;
		
	}
	
	/**
	 * 
	 * @return total de elementos por página
	 */
	public Integer getElementosPorPagina() {
		
		Integer elementosPagina = 0;
		
		if ((offset + limit) > dados.size()) {
			
			elementosPagina = ((offset + limit) - dados.size()) - limit;
			if (elementosPagina <= 0) {
				return elementosPagina * (-1);
			}
			else {
				return 0;
			}
		}
		
		return limit;
		
	}
	
	/**
	 * 
	 * @return total de páginas
	 */
	public Integer getTotalPaginas() {
		
		if (this.limit == 0)
			return 0;
		
		if (this.limit >= this.dados.size())
			return 1;
		
		Boolean incrementa = this.dados.size() % this.limit > 0;
		
		return  incrementa ? Math.incrementExact(Math.floorDiv(this.dados.size(), limit)) : Math.floorDiv(this.dados.size(), limit); 
		
	}
	
	/**
	 * 
	 * @return número da página atual
	 */
	public Integer getNumeroPagina() {
		
		if (this.limit == 0)
			return 0;
		
		if (this.offset < this.limit)
			return 1;
		
		return Math.incrementExact(Math.floorDiv(this.offset, this.limit));
		
	}
	
	/**
	 * 
	 * @return recupera o conteúdo baseado no filtro de paginação e ordenado.
	 * <p> "_content": [
	 * <p>     {
	 * <p>     	Corpo do objeto, objeto que será o json de retorno
	 * <p>     }
	 * <p> ]
	 */
	public List<Object> getConteudo() {
		
		Integer totalElementos = getElementosPorPagina();
		Integer ultimoElemento = totalElementos + offset;
		Integer primeiroElemento = getNumeroPagina() * offset; 
		
		if (totalElementos == 0 || this.offset >= ultimoElemento)
			return new ArrayList<>();
		
		ordernar();
		
		if (primeiroElemento >= ultimoElemento) 
			primeiroElemento = this.offset;
		
		if (ultimoElemento == this.dados.size() && offset == 0)
			return new ArrayList<>(this.dados);
		
		if (ultimoElemento > this.dados.size())
			ultimoElemento--;
		
		return new ArrayList<>(this.dados.subList(primeiroElemento, ultimoElemento));
		
	}
	
	private void ordernar(){
		
		List<String> sortList = separaOrdenacaoPorAtributoOrdemCrescenteDecrescente();
		
		if (!CollectionUtils.isEmpty(sortList) && this.clazz != null) {
		
	    	Collections.sort(this.dados, (o1, o2) -> {
				try {
					int comparador = ordenarOrdemCrescenteDecrescente(this.clazz.getDeclaredField(sortList.get(0).substring(1)),o1,o2,sortList.get(0).substring(0, 1));
					if (comparador != 0)
						return comparador;
					if (sortList.size() == 2) {
						return ordenarOrdemCrescenteDecrescente(this.clazz.getDeclaredField(sortList.get(1).substring(1)),o1,o2,sortList.get(1).substring(0, 1));
					}
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				return 0; 
			});
	    	
		}

	}
	
	private List<String> separaOrdenacaoPorAtributoOrdemCrescenteDecrescente() {
		
		List<String> sortList = Arrays.asList(this.sortBy.split(DELIMITADOR));
		List<String> sort = new ArrayList<>();
		
		for (String ordenacao : sortList) {

			if (!ordenacao.startsWith(CRESCENTE) && !ordenacao.startsWith(DECRESCENTE)) {
				sort.add(CRESCENTE.concat(ordenacao.trim()));
			}
			else {
				sort.add(ordenacao.trim());
			}
		
		}
		
		return sort;
		
	}
	
	private Integer ordenarOrdemCrescenteDecrescente(Field field, Object o1, Object o2, String sinal) throws IllegalArgumentException, IllegalAccessException {
		
		field.setAccessible(true);
		
		if (field.get(o1) != null && field.get(o2) != null) {
		
			if (sinal.equals(CRESCENTE)) {
				return field.get(o1).toString().compareTo(field.get(o2).toString());
			}
			
			return (-1) * field.get(o1).toString().compareTo(field.get(o2).toString());
		
		}
		
		return 0;
	}
	
}
