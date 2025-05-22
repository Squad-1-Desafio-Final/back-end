package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.entity.Produto;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T13:23:08-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class ProdutoMapperStructImpl implements ProdutoMapperStruct {

    @Override
    public Produto toEntity(CadastroProdutoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Produto produto = new Produto();

        return produto;
    }

    @Override
    public CadastroProdutoDto toProdutoDto(Produto entity) {
        if ( entity == null ) {
            return null;
        }

        String nome = null;
        BigDecimal preco = null;
        String descricao = null;
        Byte disponivel = null;
        Integer quantidade = null;

        CadastroProdutoDto cadastroProdutoDto = new CadastroProdutoDto( nome, preco, descricao, disponivel, quantidade );

        return cadastroProdutoDto;
    }

    @Override
    public CadastroProdutoDto toDadosProdutoDto(Produto entity) {
        if ( entity == null ) {
            return null;
        }

        String nome = null;
        BigDecimal preco = null;
        String descricao = null;
        Byte disponivel = null;
        Integer quantidade = null;

        CadastroProdutoDto cadastroProdutoDto = new CadastroProdutoDto( nome, preco, descricao, disponivel, quantidade );

        return cadastroProdutoDto;
    }
}
