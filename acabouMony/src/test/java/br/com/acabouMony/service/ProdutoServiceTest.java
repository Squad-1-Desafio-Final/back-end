package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroEnderecoDTO;
import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.entity.Endereco;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.mapper.EnderecoMapper;
import br.com.acabouMony.mapper.ProdutoMapperStruct;
import br.com.acabouMony.repository.EnderecoRepository;
import br.com.acabouMony.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapperStruct produtoMapperStruct;

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private Optional<Produto> produtoOptional;

    private Produto produto;
    private CadastroProdutoDto cadastroProdutoDto;

    @Test
    void criar() {
        CadastroProdutoDto dto = new CadastroProdutoDto("Produto A", BigDecimal.TEN, "Descrição A", (byte)1, 10);
        Produto produtoEntity = new Produto();
        Produto produtoSalvo = new Produto();


        when(produtoMapperStruct.toEntity(dto)).thenReturn(produtoEntity);

        when(produtoRepository.save(produtoEntity)).thenReturn(produtoSalvo);

        when(produtoMapperStruct.toProdutoDto(produtoSalvo)).thenReturn(dto);


        CadastroProdutoDto resultado = produtoService.criar(dto);


        assertNotNull(resultado);
        assertEquals(dto.nome(), resultado.nome());
        assertEquals(dto.preco(), resultado.preco());


        verify(produtoRepository).save(produtoEntity);

    }

    @Test
    void listar() {

        UUID id = UUID.randomUUID();

        Produto produto = new Produto();


        CadastroProdutoDto cadastroProdutoDto = new CadastroProdutoDto(
                "Produto Teste",
                BigDecimal.valueOf(100.0),
                "Descrição",
                (byte) 1,
                10
        );


        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        when(produtoMapperStruct.toProdutoDto(produto)).thenReturn(cadastroProdutoDto);


        var response = produtoService.listar(id);

        Assertions.assertEquals(cadastroProdutoDto, response);
    }

    @Test
    void deletar() {
        UUID id = UUID.randomUUID();

        Produto produto = new Produto();
        produto.setId(id);

        produtoService.deletar(id);

        verify(produtoRepository, times(1)).deleteById(id);
    }


    @Test
    void atualizar() {

        UUID id = UUID.randomUUID();

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome("Produto antigo");

        CadastroProdutoDto dto = new CadastroProdutoDto(
                "Produto novo",
                new BigDecimal("99.99"),
                "Descrição nova",
                (byte) 1,
                10
        );

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(id);
        produtoAtualizado.setNome(dto.nome());
        produtoAtualizado.setDescricao(dto.descricao());
        produtoAtualizado.setDisponivel(dto.disponivel());
        produtoAtualizado.setQuantidade(dto.quantidade());
        produtoAtualizado.setPreco(dto.preco());

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAtualizado);
        when(produtoMapperStruct.toProdutoDto(any(Produto.class))).thenReturn(dto);


        CadastroProdutoDto resultado = produtoService.atualizar(id, dto);


        assertNotNull(resultado);
        assertEquals("Produto novo", resultado.nome());
        verify(produtoRepository, times(1)).findById(id);
        verify(produtoRepository, times(1)).save(any(Produto.class));

    }

    @Test
    void listarTodos() {

        Produto produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setPreco(new BigDecimal("100.00"));
        produto.setDisponivel((byte) 1);
        produto.setQuantidade(10);

        CadastroProdutoDto dto = new CadastroProdutoDto(
                produto.getNome(),
                produto.getPreco(),
                produto.getDescricao(),
                produto.getDisponivel(),
                produto.getQuantidade()
        );

        when(produtoRepository.findAll()).thenReturn(List.of(produto));
        when(produtoMapperStruct.toProdutoDto(produto)).thenReturn(dto);

        List<CadastroProdutoDto> result = produtoService.listarTodos();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Produto Teste", result.get(0).nome());

        verify(produtoRepository, times(1)).findAll();
        verify(produtoMapperStruct, times(1)).toProdutoDto(produto);
    }

}