package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroTransacaoDto;
import br.com.acabouMony.dto.CartaoResumoDto;
import br.com.acabouMony.dto.ListagemTransacaoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.service.TransacaoService;
import br.com.acabouMony.tipos.TipoPagamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransacaoService transacaoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void listar() throws Exception {
        // Criando dados simulados
        UsuarioResumoDto usuario = new UsuarioResumoDto(UUID.randomUUID(), "João da Silva", "joao@email.com");
        CartaoResumoDto cartao = new CartaoResumoDto(1234, "Crédito");
        Pedido pedido = new Pedido(); // Simule o pedido como necessário
        ListagemTransacaoDto transacao = new ListagemTransacaoDto(usuario, cartao, new Date(), pedido);

        // Mock do serviço que retorna uma lista de transações
        when(transacaoService.listar()).thenReturn(List.of(transacao));

        // Performando a requisição GET para /transacao
        mockMvc.perform(get("/transacao"))
                .andExpect(status().isOk()) // Espera o código de status 200
                .andExpect(jsonPath("$[0].usuario.nome").value("João da Silva")) // Verifica se o nome do usuário está correto
                .andExpect(jsonPath("$[0].cartao.numero").value(1234)); // Verifica se o número do cartão está correto
    }

    @Test
    void cadastrar() throws Exception {

        UsuarioResumoDto usuarioResumo = new UsuarioResumoDto(UUID.randomUUID(), "Maria Oliveira", "maria@email.com");
        CartaoResumoDto cartaoResumo = new CartaoResumoDto(1234, "Crédito");

        // Criando dados simulados para o Usuario
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNome("Maria Oliveira");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("senha123");
        usuario.setCpf("12345678900");
        usuario.setTelefone("11987654321");
        usuario.setDtNasc(new Date()); // Definindo data de nascimento como a data atual

        // Criando dados simulados para o Cartao
        Cartao cartao = new Cartao();
        cartao.setId(UUID.randomUUID());
        cartao.setNumero("1234");
        cartao.setCvv(123);
        cartao.setSenha(4567);
        cartao.setValidade("12/25");
        cartao.setTipo(TipoPagamento.CREDITO);
        cartao.setAtivo(true);
        cartao.setConta(new Conta()); // Simulação de uma conta (você pode personalizar mais, se necessário)

        // Criando dados simulados para o Pedido
        Pedido pedido = new Pedido();
        pedido.setId(UUID.randomUUID());
        pedido.setUsuario(usuario);
        pedido.setPrecoTotal(150.75); // Exemplo de valor total
        pedido.setDate(new Date());
        pedido.setCarrinho(false);

        // Criando um CadastroTransacaoDto
        CadastroTransacaoDto cadastroTransacaoDto = new CadastroTransacaoDto(
                TipoPagamento.CREDITO, // Tipo de pagamento
                cartao, // Cartão utilizado
                usuario, // Usuário associado
                pedido
        );

        // Criando um ListagemTransacaoDto esperado como resposta
        ListagemTransacaoDto expectedResponse = new ListagemTransacaoDto(usuarioResumo, cartaoResumo, new Date(), pedido);

        // Mock do serviço que retorna uma transação cadastrada
        when(transacaoService.criar(any(CadastroTransacaoDto.class))).thenReturn(expectedResponse);

        // Performando a requisição POST para /transacao
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastroTransacaoDto))) // Converte o objeto para JSON
                .andExpect(status().isCreated()) // Espera o código de status 201
                .andExpect(jsonPath("$.usuario.nome").value("Maria Oliveira")) // Verifica se o nome do usuário está correto
                .andExpect(jsonPath("$.cartao.numero").value("1234")); // Verifica se o número do cartão está correto
    }



}