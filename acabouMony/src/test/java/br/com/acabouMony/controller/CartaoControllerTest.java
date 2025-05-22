package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.CadastroEnderecoDTO;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                234
        );

        String json = objectMapper.writeValueAsString(dto);

        var response = mockMvc.perform(post("/cartao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(cartaoService).criar(any());


    }
}

