package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.dto.CadastroUsuarioDTO;
import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
//    }
//
    @Test
    void listar() throws Exception {
        Produto produtoMock = new Produto(
                UUID.randomUUID(),
                "Produto Exemplo",
                new BigDecimal("99.90"),
                "Descrição do produto",
                (byte) 1,
                5
        );

        UsuarioResumoDto usuarioMock = new UsuarioResumoDto(
                UUID.randomUUID(),
                "João da Silva",
                "joao@email.com"
        );

        ListagemPedidoDto dtoMock = new ListagemPedidoDto(
                UUID.randomUUID(),
                usuarioMock,
                List.of(produtoMock),
                99.90,
                LocalDateTime.now(),
                true
        );

        when(pedidoService.listar()).thenReturn(List.of(dtoMock));

        mockMvc.perform(get("/pedido"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuario.nome").value("João da Silva"))
                .andExpect(jsonPath("$[0].produtos[0].nome").value("Produto Exemplo"));
    }

    @Test
    void criar() throws Exception {
        // Criando um mock de Usuario
        Usuario usuarioMock = new Usuario(new CadastroUsuarioDTO(
                "João da Silva", "joao@email.com", "senha123", "12345678901", "11987654321", Date.valueOf("1990-01-01")
        ));
        UsuarioResumoDto usuarioMockResumo = new UsuarioResumoDto(
                UUID.randomUUID(),
                "João da Silva",
                "joao@email.com"
        );

        // Criando um mock de Produto
        Produto produtoMock = new Produto(
                UUID.randomUUID(),
                "Produto Exemplo",
                new BigDecimal("99.90"),
                "Descrição do produto",
                (byte) 1,
                5
        );

        // Criando o DTO de CadastroPedidoDto com o usuario e lista de produtos
        CadastroPedidoDto dadosPedido = new CadastroPedidoDto(
                usuarioMock,
                List.of(produtoMock)
        );

        ListagemPedidoDto dtoMock = new ListagemPedidoDto(
                UUID.randomUUID(),
                usuarioMockResumo,
                List.of(produtoMock),
                99.90,
                LocalDateTime.now(),
                true
        );

        // Quando o serviço 'criar' for chamado, retornamos um pedido simulado
        when(pedidoService.criar(any(CadastroPedidoDto.class))).thenReturn(dtoMock);

        // Realizando a requisição POST
        mockMvc.perform(post("/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosPedido)))
                .andExpect(status().isCreated()) // Espera status 201 Created
                .andExpect(jsonPath("$.id").exists()) // Espera que o campo 'id' exista no JSON de resposta
                .andExpect(jsonPath("$.usuario.nome").value("João da Silva")); // Verifica o nome do usuário na resposta
    }

    @Test
    void concluirTransacao() throws Exception {
        UUID pedidoId = UUID.randomUUID();

        Produto produtoMock = new Produto(
                UUID.randomUUID(),
                "Produto Exemplo",
                new BigDecimal("99.90"),
                "Descrição do produto",
                (byte) 1,
                5
        );

        UsuarioResumoDto usuarioMock = new UsuarioResumoDto(
                UUID.randomUUID(),
                "João da Silva",
                "joao@email.com"
        );

        ListagemPedidoDto dtoMock = new ListagemPedidoDto(
                pedidoId,
                usuarioMock,
                List.of(produtoMock),
                99.90,
                LocalDateTime.now(),
                false // carrinho ainda não finalizado
        );

        // Quando o serviço 'concluirTransacao' for chamado, retornamos um pedido simulado
        when(pedidoService.concluirTransacao(pedidoId)).thenReturn(dtoMock);

        // Realizando a requisição PATCH
        mockMvc.perform(patch("/pedido/concluir-transacao/{id}", pedidoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera status 200 OK
                .andExpect(jsonPath("$.id").exists()) // Espera que o campo 'id' exista no JSON de resposta
                .andExpect(jsonPath("$.carrinho").value(false)); // Verifica se o carrinho foi concluído
    }


    @Test
    void editar() throws Exception {
        UUID pedidoId = UUID.randomUUID();

        Produto produtoMock = new Produto(
                UUID.randomUUID(),
                "Produto Exemplo",
                new BigDecimal("99.90"),
                "Descrição do produto",
                (byte) 1,
                5
        );

        UsuarioResumoDto usuarioMock = new UsuarioResumoDto(
                UUID.randomUUID(),
                "João da Silva",
                "joao@email.com"
        );

        Pedido dadosEditados = new Pedido(
                // Preencha com os dados necessários para o Pedido editado
        );

        ListagemPedidoDto dtoMock = new ListagemPedidoDto(
                pedidoId,
                usuarioMock,
                List.of(produtoMock),
                99.90,
                LocalDateTime.now(),
                true // carrinho já concluído após edição
        );

        // Quando o serviço 'editar' for chamado, retornamos um pedido simulado editado
        when(pedidoService.editar(eq(pedidoId), any(Pedido.class))).thenReturn(dtoMock);

        // Realizando a requisição PATCH
        mockMvc.perform(patch("/pedido/editar/{id}", pedidoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosEditados)))
                .andExpect(status().isOk()) // Espera status 200 OK
                .andExpect(jsonPath("$.id").exists()) // Espera que o campo 'id' exista no JSON de resposta
                .andExpect(jsonPath("$.carrinho").value(true)); // Verifica se o carrinho foi editado
    }

}
