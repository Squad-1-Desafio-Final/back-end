package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.service.CartaoService;
import br.com.acabouMony.service.ProdutoService;
import br.com.acabouMony.tipos.TipoPagamento;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criandoProduto() throws Exception {
        CadastroProdutoDto dto = new CadastroProdutoDto(
                "Produto teste",
                BigDecimal.ZERO,
                "Descrição",
                (byte) 100,
                10
        );

        String json = objectMapper.writeValueAsString(dto);

        var response = mockMvc.perform(post("/produto/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(produtoService).criar(any());
    }

    @Test
    void listarProduto() throws Exception {
        UUID id = UUID.randomUUID();

        CadastroProdutoDto produtoDto = new CadastroProdutoDto(
                "Produto Teste",
                new BigDecimal("100.00"),
                "Descrição do Produto Teste",
                (byte) 1,
                10
        );

        when(produtoService.listar(id)).thenReturn(produtoDto);

        mockMvc.perform(get("/produto/listar/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(produtoDto)));
    }

    @Test
    void atualizarProduto() throws Exception {
        UUID id = UUID.randomUUID();

        CadastroProdutoDto produtoDto = new CadastroProdutoDto(
                "Produto Atualizado",
                new BigDecimal("120.00"),
                "Descrição do Produto Atualizado",
                (byte) 1,
                15
        );

        when(produtoService.atualizar(id, produtoDto)).thenReturn(produtoDto);


        mockMvc.perform(patch("/produto/atualizar/" + id)
                        .content(objectMapper.writeValueAsString(produtoDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(produtoDto)));
    }

    @Test
    void deletarProduto() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(produtoService).deletar(id);

        mockMvc.perform(delete("/produto/excluir/" + id))
                .andExpect(status().isNoContent());

        Mockito.verify(produtoService).deletar(id);


    }

    @Test
    void listarTodos() throws Exception {
        UUID id = UUID.randomUUID();

        CadastroProdutoDto produtoDto = new CadastroProdutoDto(
                "Produto Atualizado",
                new BigDecimal("120.0"),
                "Descrição do Produto Atualizado",
                (byte) 1,
                15
        );


        when(produtoService.atualizar(id, produtoDto)).thenReturn(produtoDto);


        mockMvc.perform(patch("/produto/atualizar/" + id)
                        .content(objectMapper.writeValueAsString(produtoDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Atualizado"))
                .andExpect(jsonPath("$.preco").value("120.0"))
                .andExpect(jsonPath("$.descricao").value("Descrição do Produto Atualizado"))
                .andExpect(jsonPath("$.disponivel").value(1))
                .andExpect(jsonPath("$.quantidade").value(15));
    }



}