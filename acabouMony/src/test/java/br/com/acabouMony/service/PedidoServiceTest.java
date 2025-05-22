package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.exception.PedidoNaoEncontrado;
import br.com.acabouMony.exception.PedidoNaoPodeSerEditadoException;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.mapper.PedidoCadastrarMapperStruct;
import br.com.acabouMony.mapper.PedidoListarMapperStruct;
import br.com.acabouMony.repository.PedidoRepository;
import br.com.acabouMony.repository.ProdutoRepository;
import br.com.acabouMony.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoCadastrarMapperStruct pedidoCadastrarMapperStruct;

    @Mock
    private PedidoListarMapperStruct pedidoListarMapperStruct;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    private Usuario usuario;
    private Produto produto1;
    private Produto produto2;
    private Pedido pedido;
    private CadastroPedidoDto cadastroPedidoDto;

    @BeforeEach
    void setup() {
        usuario = mock(Usuario.class);
        UUID usuarioId = UUID.randomUUID();
        lenient().when(usuario.getId()).thenReturn(usuarioId);

        produto1 = mock(Produto.class);
        UUID produto1Id = UUID.randomUUID();
        lenient().when(produto1.getId()).thenReturn(produto1Id);
        lenient().when(produto1.getPreco()).thenReturn(BigDecimal.valueOf(10.0));

        produto2 = mock(Produto.class);
        UUID produto2Id = UUID.randomUUID();
        lenient().when(produto2.getId()).thenReturn(produto2Id);
        lenient().when(produto2.getPreco()).thenReturn(BigDecimal.valueOf(15.0));

        List<Produto> produtos = List.of(produto1, produto2);

        pedido = new Pedido();
        pedido.setId(UUID.randomUUID());
        pedido.setUsuario(usuario);
        pedido.setProdutos(produtos);
        pedido.setPrecoTotal(25.0);
        pedido.setCarrinho(true);
        pedido.setDate(new Date());

        cadastroPedidoDto = mock(CadastroPedidoDto.class);
        lenient().when(cadastroPedidoDto.usuario()).thenReturn(usuario);
        lenient().when(cadastroPedidoDto.produtos()).thenReturn(produtos);
    }



    @Test
    void listar_deveRetornarListaDePedidos() {
        List<Pedido> pedidos = List.of(pedido);
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        ListagemPedidoDto dto = mock(ListagemPedidoDto.class);
        when(pedidoListarMapperStruct.toPedidoDto(pedido)).thenReturn(dto);

        List<ListagemPedidoDto> resultado = pedidoService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(dto, resultado.get(0));

        verify(pedidoRepository).findAll();
        verify(pedidoListarMapperStruct).toPedidoDto(pedido);
    }

    @Test
    void criar_deveCriarPedidoComSucesso() {
        // Mock findById do usuário
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        // Mock produtos
        List<Produto> produtos = Arrays.asList(produto1, produto2);
        List<UUID> ids = Arrays.asList(produto1.getId(), produto2.getId());
        when(cadastroPedidoDto.produtos()).thenReturn(produtos);
        when(produtoRepository.findAllById(ids)).thenReturn(produtos);

        Pedido pedidoParaSalvar = new Pedido();
        when(pedidoCadastrarMapperStruct.toEntity(cadastroPedidoDto)).thenReturn(pedidoParaSalvar);

        Pedido pedidoSalvo = new Pedido();
        pedidoSalvo.setId(UUID.randomUUID());
        when(pedidoRepository.save(any())).thenReturn(pedidoSalvo);

        ListagemPedidoDto dtoRetornado = mock(ListagemPedidoDto.class);
        when(pedidoListarMapperStruct.toPedidoDto(pedidoSalvo)).thenReturn(dtoRetornado);

        ListagemPedidoDto resultado = pedidoService.criar(cadastroPedidoDto);

        assertNotNull(resultado);
        assertEquals(dtoRetornado, resultado);

        verify(usuarioRepository).findById(usuario.getId());
        verify(produtoRepository).findAllById(ids);
        verify(pedidoCadastrarMapperStruct).toEntity(cadastroPedidoDto);
        verify(pedidoRepository).save(any());
        verify(pedidoListarMapperStruct).toPedidoDto(pedidoSalvo);
    }


    @Test
    void editar_deveEditarPedidoQuandoCarrinhoTrue() {
        UUID id = pedido.getId();
        Pedido dados = new Pedido();
        dados.setProdutos(pedido.getProdutos());

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        ListagemPedidoDto dto = mock(ListagemPedidoDto.class);
        when(pedidoListarMapperStruct.toPedidoDto(pedido)).thenReturn(dto);

        ListagemPedidoDto resultado = pedidoService.editar(id, dados);

        assertNotNull(resultado);
        assertEquals(dto, resultado);

        verify(pedidoRepository).findById(id);
        verify(pedidoRepository).save(pedido);
        verify(pedidoListarMapperStruct).toPedidoDto(pedido);
    }

    @Test
    void editar_deveLancarExceptionQuandoCarrinhoFalse() {
        UUID id = pedido.getId();
        pedido.setCarrinho(false);
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        assertThrows(PedidoNaoPodeSerEditadoException.class, () -> pedidoService.editar(id, new Pedido()));

        verify(pedidoRepository).findById(id);
        verifyNoMoreInteractions(pedidoRepository);
    }

    @Test
    void concluirTransacao_deveAtualizarPedido() {
        UUID id = pedido.getId();
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        ListagemPedidoDto dto = mock(ListagemPedidoDto.class);
        when(pedidoListarMapperStruct.toPedidoDto(pedido)).thenReturn(dto);

        ListagemPedidoDto resultado = pedidoService.concluirTransacao(id);

        assertNotNull(resultado);
        assertEquals(dto, resultado);
        assertFalse(pedido.getCarrinho());

        verify(pedidoRepository).findById(id);
        verify(pedidoRepository).save(pedido);
        verify(pedidoListarMapperStruct).toPedidoDto(pedido);
    }

}
