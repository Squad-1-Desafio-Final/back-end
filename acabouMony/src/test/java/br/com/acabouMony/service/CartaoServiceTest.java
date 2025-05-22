package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.mapper.CartaoMapperStruct;
import br.com.acabouMony.mapper.ProdutoMapperStruct;
import br.com.acabouMony.repository.CartaoRepository;
import br.com.acabouMony.repository.ContaRepository;
import br.com.acabouMony.repository.ProdutoRepository;
import br.com.acabouMony.tipos.TipoPagamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private CartaoMapperStruct cartaoMapperStruct;

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private CartaoService cartaoService;

    @Test
    void saveCartao() {
    }

    @Test
    void gerarDataValidadeFormatada() {
    }

    @Test
    void gerarNumeroCartao() {
    }

    @Test
    void gerarCVV() {
    }

    @Test
    void criar() {
        CadastroCartaoDTO dto = new CadastroCartaoDTO(1234, TipoPagamento.CREDITO, 123);

        Conta conta = new Conta();
        conta.setNumero(123);

        Cartao cartaoEntity = new Cartao();
        Cartao cartaoSalvo = new Cartao();

        CadastroCartaoDTO retornoDto = new CadastroCartaoDTO(1234, TipoPagamento.CREDITO, 123);

        when(contaRepository.findByNumero(dto.numeroConta())).thenReturn(Optional.of(conta));
        when(cartaoMapperStruct.toEntity(dto)).thenReturn(cartaoEntity);
        when(cartaoRepository.save(cartaoEntity)).thenReturn(cartaoSalvo);
        when(cartaoMapperStruct.toCartaoDto(any(Cartao.class))).thenReturn(retornoDto);

        CadastroCartaoDTO resultado = cartaoService.criar(dto);

        assertNotNull(resultado);
        assertEquals(dto.numeroConta(), resultado.numeroConta());
        assertEquals(dto.tipo(), resultado.tipo());

        verify(cartaoRepository).save(cartaoEntity);
    }

    @Test
    void listar() {

        Cartao cartao = new Cartao();
        cartao.setId(UUID.randomUUID());
        cartao.setTipo(TipoPagamento.CREDITO);
        cartao.setSenha(1005);

    }

    @Test
    void listarPorId() {
    }

    @Test
    void editar() {
    }
}