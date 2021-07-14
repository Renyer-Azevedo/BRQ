package com.brq.santander.cadeiasocietaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brq.santander.cadeiasocietaria.dto.CorporateChainDto;
import com.brq.santander.cadeiasocietaria.model.CorporateChain;
import com.brq.santander.cadeiasocietaria.service.CorporateChainService;

@RestController
@RequestMapping(value = "/corporatechain")
public class CorporateChainController {
	
    private CorporateChainService corporateChainService;
   
    @Autowired
    public CorporateChainController(CorporateChainService corporateChainService) {
		super();
		this.corporateChainService = corporateChainService;
	}

	@GetMapping(value = "/")
    @ResponseBody
    public List<CorporateChain> listarTodos() {
        return corporateChainService.listarTodos();
    }
    
    @GetMapping(value = "/{penumpri}")
    @ResponseBody
    public List<CorporateChainDto> buscarCorporateChainPorPenumpri(@PathVariable String penumpri) {
        return corporateChainService.buscarCorporateChainPorPenumpri(penumpri);
    }

}
