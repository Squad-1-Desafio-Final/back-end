package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.CadastroEnderecoDTO;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.service.CartaoService;
import br.com.acabouMony.tipos.TipoPagamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class CartaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CartaoService cartaoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Deveria retornar 201 ao cadastrar um cartão")
    void criarcartao() throws Exception {
        CadastroCartaoDTO dto = new CadastroCartaoDTO(
                123,
                TipoPagamento.CREDITO,
                56669
        );

        String json = objectMapper.writeValueAsString(dto);

        var response = mockMvc.perform(post("/cartao/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(cartaoService).criar(any());


    }


    @Test
    void listarCartao() throws Exception {
        List<ListagemCartaoDTO> listaCartao = List.of(
                new ListagemCartaoDTO("123456", TipoPagamento.CREDITO),
                new ListagemCartaoDTO("654321", TipoPagamento.DEBITO)
        );

        when(cartaoService.listar()).thenReturn(listaCartao);

        mockMvc.perform(get("/cartao/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(listaCartao)));
    }

    @Test
    void listarPorId() throws Exception {
        UUID id = UUID.randomUUID();

        ListagemCartaoDTO dtoEsperado = new ListagemCartaoDTO("1234 5678 9012 3456", TipoPagamento.CREDITO);

        when(cartaoService.listarPorId(id)).thenReturn(dtoEsperado);

        mockMvc.perform(get("/cartao/listar/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dtoEsperado)));
    }


    @Test
    void editar() throws Exception {
        UUID id = UUID.randomUUID();

        Cartao cartaoEncontrado = new Cartao();
        cartaoEncontrado.setId(id);
        cartaoEncontrado.setAtivo(true);

        when(cartaoService.editar(id)).thenReturn(cartaoEncontrado);

        mockMvc.perform(patch("/cartao/desativar/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cartaoEncontrado)));
    }




}








