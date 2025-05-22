package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.dto.ListagemContaDTO;
import br.com.acabouMony.entity.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    //@Mapping(source = "numero", target = "numero")
    Conta toEntity(CadastroContaDTO dto);

    ListagemContaDTO toListagemContaDTO(Conta conta);
}
