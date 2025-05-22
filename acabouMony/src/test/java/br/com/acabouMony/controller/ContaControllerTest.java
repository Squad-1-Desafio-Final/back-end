package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.service.ContaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContaService contaService;

    @Mock
    private CadastroContaDTO cadastroContaDTO;

    @Test
    @DisplayName("Deveria retorna 201 ao cadastrar uma conta")
    void saveConta() throws Exception {

        var response = mockMvc.perform(post("/conta")
                .content(String.valueOf(cadastroContaDTO))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

            Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    void getAllContas() {
    }

    @Test
    void getOneConta() {
    }

    @Test
    void updateConta() {
    }

    @Test
    void deleteConta() {
    }

    @Test
    void deleteLogicaConta() {
    }
}