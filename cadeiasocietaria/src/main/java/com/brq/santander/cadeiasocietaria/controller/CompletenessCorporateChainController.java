package com.brq.santander.cadeiasocietaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brq.santander.cadeiasocietaria.dto.CompletenessCorporateChainDTO;
import com.brq.santander.cadeiasocietaria.service.CompletenessCorporateChainService;

@RestController
@RequestMapping(value = "/completenesscorporatechain")
public class CompletenessCorporateChainController {
	
	
	private CompletenessCorporateChainService completenessCorporateChainService;
	
	
	@Autowired
    public CompletenessCorporateChainController(CompletenessCorporateChainService completenessCorporateChainService) {
		super();
		this.completenessCorporateChainService = completenessCorporateChainService;
	}



	@GetMapping(value = "/{penumpri}")
    @ResponseBody
    public List<CompletenessCorporateChainDTO> buscarCompletenessCorporateChainPorPenumpri(@PathVariable String penumpri) {
        return completenessCorporateChainService.buscarCompletenessCorporateChainPorPenumpri(penumpri);
    }

}
