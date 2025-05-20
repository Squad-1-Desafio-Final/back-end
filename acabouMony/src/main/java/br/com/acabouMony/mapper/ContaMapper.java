package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.dto.ListagemContaDTO;
import br.com.acabouMony.entity.Conta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    Conta toEntity(CadastroContaDTO dto);

    ListagemContaDTO toListagemContaDTO(Conta conta);
}
