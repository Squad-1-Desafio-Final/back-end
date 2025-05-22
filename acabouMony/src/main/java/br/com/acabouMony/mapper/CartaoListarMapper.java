package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartaoListarMapper {

//    @Mapping(source = "numero", target = "numero")
    Cartao toEntity(ListagemCartaoDTO dto);

    ListagemCartaoDTO toCartaoDto(Cartao entity);

    ListagemCartaoDTO toDadosCartaoDto(Cartao entity);
}
