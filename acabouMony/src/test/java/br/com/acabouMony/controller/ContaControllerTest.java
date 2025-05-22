package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.service.ContaService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContaService contaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deveria retorna 201 ao cadastrar uma conta")
    void saveConta() throws Exception {

        CadastroContaDTO dto = new CadastroContaDTO(LocalDate.now(),
                1500.50,
                1234,
                12345678,
                33,
                "123.456.789-00");

    var response = mockMvc.perform(post("/conta")
            .content(objectMapper.writeValueAsString(dto))
            .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

            Assertions.assertEquals(201,response.getStatus());
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