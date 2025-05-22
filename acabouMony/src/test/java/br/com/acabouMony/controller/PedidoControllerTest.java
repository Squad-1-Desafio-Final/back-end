package br.com.acabouMony.controller;

import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    void listar() throws Exception {
        // Configuração do mock
        ListagemPedidoDto pedidoDto = new ListagemPedidoDto(
                UUID.randomUUID(),
                null, // Usuário mockado
                Collections.emptyList(),
                0.0,
                null, // Data mockada
                false
        );
        when(pedidoService.listar()).thenReturn(Collections.singletonList(pedidoDto));

        // Execução e verificação
        mockMvc.perform(get("/pedido"))
                .andExpect(status().isOk());
    }

    @Test
    void criar() throws Exception {
        // Dados de entrada
        String pedidoJson = "{\"usuarioId\": \"123e4567-e89b-12d3-a456-426614174000\", \"produtos\": []}";

        // Execução e verificação
        mockMvc.perform(post("/pedido")
                        .contentType("application/json")
                        .content(pedidoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void concluirTransacao() throws Exception {
        // Execução e verificação
        mockMvc.perform(patch("/pedido/concluir-transacao/{id}", UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void editar() throws Exception {
        // Dados de entrada
        String pedidoJson = "{\"usuarioId\": \"123e4567-e89b-12d3-a456-426614174000\", \"produtos\": []}";

        // Execução e verificação
        mockMvc.perform(patch("/pedido/editar/{id}", UUID.randomUUID())
                        .contentType("application/json")
                        .content(pedidoJson))
                .andExpect(status().isOk());
    }
}
