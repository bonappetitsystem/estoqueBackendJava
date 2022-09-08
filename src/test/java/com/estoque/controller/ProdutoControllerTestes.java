package com.estoque.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityNotFoundException;

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

import com.estoque.entidades.Produto;
import com.estoque.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTestes {
	private Long idExistente;
	private Long idNaoExistente;

	private Produto pExistente;
	private Produto pNovo;
	
	@Autowired
	private ObjectMapper objMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProdutoService service;
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		
		pNovo = new Produto();
		pExistente = new Produto();
		pExistente.setId(1L);;
		
		Mockito.when(service.buscarPorId(idExistente)).thenReturn(pExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(service).buscarPorId(idNaoExistente);
		
		Mockito.when(service.cadastrar(any())).thenReturn(pExistente);
		
		Mockito.when(service.alterar(eq(idExistente), any())).thenReturn(pExistente);
		Mockito.when(service.alterar(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	}
	
	@Test
	public void retornaEstoqueAoConsultarIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/produtos/{id}", idExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveRetornar404() throws Exception {
		ResultActions result = mockMvc.perform(get("/{idExistente}", idNaoExistente).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornar204SalvoComsucesso() throws Exception {
		String jsonBody = objMapper.writeValueAsString(pNovo);

		ResultActions result = mockMvc.perform(post("/produtos")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void retornaOkQuandoAltera() throws Exception {
		String jsonBody = objMapper.writeValueAsString(pNovo);
		ResultActions result = mockMvc.perform(put("/produtos/{id}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void retorna404QuandoAlteraInexistente() throws Exception {
		String jsonBody = objMapper.writeValueAsString(pNovo);
		ResultActions result = mockMvc.perform(put("/{idExistente}", idNaoExistente).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornaListaConsultaTodos() throws Exception {
		ResultActions result = mockMvc.perform(get("/produtos").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
}
