package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartaoMapperStruct {

    Cartao toEntity(CadastroCartaoDTO dto);
    CadastroCartaoDTO toCartaoDto(Cartao entity);
    CadastroCartaoDTO toDadosCartaoDto(Cartao entity);

}
