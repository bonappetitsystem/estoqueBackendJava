package com.estoque.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import com.estoque.entidades.UnidadeMedida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.estoque.entidades.Estoque;
import com.estoque.service.EstoqueService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EstoqueControllerTest {
	private Long idExistente;
	private Long idNaoExistente;

	private Estoque eNova;
	
	@Autowired
	private ObjectMapper objMapper;
	
	@Autowired
	private MockMvc nockMvc;
	
	@MockBean
	private EstoqueService service;

	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		
		eNova = new Estoque();
		Estoque eExistente = new Estoque(100.0f, 10, new Date(23 - 12 - 2040), "DKFRJ", UnidadeMedida.KG, 3.98f);
		eExistente.setId(idExistente);
		
		Mockito.when(service.buscarPorId(idExistente)).thenReturn(eExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(service).buscarPorId(idNaoExistente);
		
		Mockito.when(service.cadastrar(any())).thenReturn(eExistente);
		
		Mockito.when(service.alterar(eq(idExistente), any())).thenReturn(eExistente);
		Mockito.when(service.alterar(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	}
	
	@Test
	public void retornaEstoqueAoConsultarIdExistente() throws Exception {
		ResultActions result = nockMvc.perform(get("/estoques/{id}", idExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveRetornar404() throws Exception {
		ResultActions result = nockMvc.perform(get("/{idExistente}", idNaoExistente).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornar204SalvoComsucesso() throws Exception {
		String jsonBody = objMapper.writeValueAsString(eNova);

		ResultActions result = nockMvc.perform(post("/estoques")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void retornaOkQuandoAltera() throws Exception {
		String jsonBody = objMapper.writeValueAsString(eNova);
		ResultActions result = nockMvc.perform(put("/estoques/{id}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void retorna404QuandoAlteraInexistente() throws Exception {
		String jsonBody = objMapper.writeValueAsString(eNova);
		ResultActions result = nockMvc.perform(put("/{idExistente}", idNaoExistente).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornaListaConsultaTodos() throws Exception {
		ResultActions result = nockMvc.perform(get("/estoques").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}


	
}
