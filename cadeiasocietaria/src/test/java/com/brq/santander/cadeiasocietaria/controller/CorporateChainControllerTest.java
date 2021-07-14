package com.brq.santander.cadeiasocietaria.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.brq.santander.cadeiasocietaria.dto.CorporateChainDto;
import com.brq.santander.cadeiasocietaria.service.CorporateChainService;

@RunWith(SpringRunner.class)
@WebMvcTest(CorporateChainController.class)
public class CorporateChainControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CorporateChainService corporateChainService;
    
    private List<CorporateChainDto> corporateChainDtoList;
    
    @Before
    public void init() {

    	corporateChainDtoList.add(new CorporateChainDto("SO", "14", "030414024000158", "PADARIA PADOCA", "ADRIANO FREIRES ROSSO", "0000775M", "030414024000158", "T803H15", "personas", 3));
    	
    }
    
    
    @Test
    public void callingWithoutParameterShouldReturnBadRequest() throws Exception {

    	Mockito.when(corporateChainService.buscarCorporateChainPorPenumpri("")).thenReturn(corporateChainDtoList);
        this.mockMvc.perform(get("/cadeiasocietaria/corporatechain/00066557")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
