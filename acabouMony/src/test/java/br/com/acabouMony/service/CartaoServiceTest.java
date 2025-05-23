package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.exception.CartaoNaoEncontrado;
import br.com.acabouMony.exception.IdNaoEncontradoException;
import br.com.acabouMony.mapper.CartaoListarMapper;
import br.com.acabouMony.mapper.CartaoMapperStruct;
import br.com.acabouMony.mapper.ProdutoMapperStruct;
import br.com.acabouMony.repository.CartaoRepository;
import br.com.acabouMony.repository.ContaRepository;
import br.com.acabouMony.repository.ProdutoRepository;
import br.com.acabouMony.tipos.TipoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CartaoServiceTest {

    @InjectMocks
    private CartaoService cartaoService;

    @Mock
    private CartaoRepository repository;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private CartaoMapperStruct cartaoMapperStruct;

    @Mock
    private CartaoListarMapper cartaoListarMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCartao_DeveSalvarERetornarDTO() {
        CadastroCartaoDTO dto = new CadastroCartaoDTO(1234, TipoPagamento.DEBITO, 1); // preencha os campos necessários

        Cartao cartao = new Cartao(dto);

        when(repository.save(any(Cartao.class))).thenReturn(cartao);
        when(cartaoMapperStruct.toCartaoDto(any(Cartao.class))).thenReturn(dto);

        CadastroCartaoDTO result = cartaoService.saveCartao(dto);

        assertNotNull(result);
        verify(repository).save(any(Cartao.class));


        ArgumentCaptor<Cartao> cartaoCaptor = ArgumentCaptor.forClass(Cartao.class);
        verify(cartaoMapperStruct).toCartaoDto(cartaoCaptor.capture());

        Cartao capturado = cartaoCaptor.getValue();
        assertEquals(cartao.getNumero(), capturado.getNumero());
    }

    @Test
    void testCriar_DeveCriarCartaoERetornarDTO() {
        CadastroCartaoDTO dto = mock(CadastroCartaoDTO.class);
        when(dto.numeroConta()).thenReturn(123);

        Cartao cartao = new Cartao();
        Conta conta = new Conta();

        when(cartaoMapperStruct.toEntity(dto)).thenReturn(cartao);
        when(contaRepository.findByNumero(123)).thenReturn(Optional.of(conta));
        when(repository.save(any(Cartao.class))).thenReturn(cartao);
        when(cartaoMapperStruct.toCartaoDto(any(Cartao.class))).thenReturn(dto);

        CadastroCartaoDTO result = cartaoService.criar(dto);

        assertNotNull(result);
        verify(repository).save(cartao);
    }

    @Test
    void testListar_DeveRetornarListaCartoes() {
        List<ListagemCartaoDTO> lista = List.of(mock(ListagemCartaoDTO.class));
        when(repository.listarNumETipo()).thenReturn(lista);

        List<ListagemCartaoDTO> result = cartaoService.listar();

        assertEquals(1, result.size());
        verify(repository).listarNumETipo();
    }

    @Test
    void testListarPorId_ComIdValido_DeveRetornarDTO() {
        UUID id = UUID.randomUUID();
        Cartao cartao = new Cartao();
        ListagemCartaoDTO dto = mock(ListagemCartaoDTO.class);

        when(repository.findById(id)).thenReturn(Optional.of(cartao));
        when(cartaoListarMapper.toCartaoDto(cartao)).thenReturn(dto);

        ListagemCartaoDTO result = cartaoService.listarPorId(id);

        assertNotNull(result);
        verify(repository).findById(id);
    }

    @Test
    void testListarPorId_ComIdInvalido_DeveLancarExcecao() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IdNaoEncontradoException.class, () -> cartaoService.listarPorId(id));
    }

    @Test
    void testEditar_ComIdValido_DeveDesativarCartao() {
        UUID id = UUID.randomUUID();
        Cartao cartao = new Cartao();
        cartao.setAtivo(true);

        when(repository.findById(id)).thenReturn(Optional.of(cartao));
        when(repository.save(any(Cartao.class))).thenReturn(cartao);

        Cartao result = cartaoService.editar(id);

        assertFalse(result.isAtivo());
        verify(repository).save(cartao);
    }

    @Test
    void testEditar_ComIdInvalido_DeveLancarExcecao() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CartaoNaoEncontrado.class, () -> cartaoService.editar(id));
    }
}