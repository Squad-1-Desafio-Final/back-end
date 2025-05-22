package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroTransacaoDto;
import br.com.acabouMony.dto.ListagemTransacaoDto;
import br.com.acabouMony.entity.*;
import br.com.acabouMony.exception.CartaoNaoEncontrado;
import br.com.acabouMony.exception.PedidoNaoEncontrado;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.mapper.TransacaoCadastroMapper;
import br.com.acabouMony.mapper.TransacaoListarMapper;
import br.com.acabouMony.repository.*;
import br.com.acabouMony.tipos.TipoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private TransacaoCadastroMapper transacaoCadastroMapper;

    @Mock
    private TransacaoListarMapper transacaoListarMapper;

    private Usuario usuario;
    private Cartao cartao;
    private Pedido pedido;
    private Transacao transacao;
    private CadastroTransacaoDto cadastroTransacaoDto;

    @BeforeEach
    void setup() {
        usuario = mock(Usuario.class);
        UUID usuarioId = UUID.randomUUID();
        lenient().when(usuario.getId()).thenReturn(usuarioId);

        cartao = mock(Cartao.class);
        UUID cartaoId = UUID.randomUUID();
        lenient().when(cartao.getId()).thenReturn(cartaoId);

        pedido = mock(Pedido.class);
        UUID pedidoId = UUID.randomUUID();
        lenient().when(pedido.getId()).thenReturn(pedidoId);

        cadastroTransacaoDto = mock(CadastroTransacaoDto.class);
        lenient().when(cadastroTransacaoDto.usuario()).thenReturn(usuario);
        lenient().when(cadastroTransacaoDto.cartao()).thenReturn(cartao);
        lenient().when(cadastroTransacaoDto.pedido()).thenReturn(pedido);
        lenient().when(cadastroTransacaoDto.tipo()).thenReturn(TipoPagamento.CREDITO);

        transacao = new Transacao();
    }


    @Test
    void criar_deveCriarTransacaoComSucesso() {
        // Configura os retornos dos repositories com os mocks
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(cartaoRepository.findById(cartao.getId())).thenReturn(Optional.of(cartao));
        when(pedidoRepository.findById(pedido.getId())).thenReturn(Optional.of(pedido));
        when(transacaoCadastroMapper.toEntity(cadastroTransacaoDto)).thenReturn(transacao);
        when(transacaoRepository.save(any(Transacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ListagemTransacaoDto dtoMock = mock(ListagemTransacaoDto.class);
        when(transacaoListarMapper.toTransacaoDto(any(Transacao.class))).thenReturn(dtoMock);

        // Executa o método a testar
        ListagemTransacaoDto resultado = transacaoService.criar(cadastroTransacaoDto);

        // Verificações básicas
        assertNotNull(resultado);
        assertEquals(dtoMock, resultado);

        // Verifica se as interações ocorreram corretamente
        verify(usuarioRepository).findById(usuario.getId());
        verify(cartaoRepository).findById(cartao.getId());
        verify(pedidoRepository).findById(pedido.getId());
        verify(transacaoCadastroMapper).toEntity(cadastroTransacaoDto);
        verify(transacaoRepository).save(transacao);
        verify(transacaoListarMapper).toTransacaoDto(transacao);

        // Checa se os setters foram chamados no objeto transacao
        assertEquals(cartao, transacao.getCartao());
        assertEquals(pedido, transacao.getPedido());
        assertEquals(usuario, transacao.getDestinatario());
        assertEquals(TipoPagamento.CREDITO, transacao.getTipo());
        assertNotNull(transacao.getData());
    }

    @Test
    void listar_deveRetornarListaDeTransacoes() {
        List<Transacao> transacoes = List.of(transacao);
        when(transacaoRepository.findAll()).thenReturn(transacoes);

        ListagemTransacaoDto dtoMock = mock(ListagemTransacaoDto.class);
        when(transacaoListarMapper.toTransacaoDto(transacao)).thenReturn(dtoMock);

        List<ListagemTransacaoDto> resultado = transacaoService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(dtoMock, resultado.get(0));

        verify(transacaoRepository).findAll();
        verify(transacaoListarMapper).toTransacaoDto(transacao);
    }
}
