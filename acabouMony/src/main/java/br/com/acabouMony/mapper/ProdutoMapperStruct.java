package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.entity.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapperStruct {

    Produto toEntity(CadastroProdutoDto dto);
    CadastroProdutoDto toProdutoDto(Produto entity);
    CadastroProdutoDto toDadosProdutoDto(Produto entity);

}
