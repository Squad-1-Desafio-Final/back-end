package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroEnderecoDTO;
import br.com.acabouMony.dto.ListagemEnderecoDTO;
import br.com.acabouMony.entity.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(CadastroEnderecoDTO dto);

    ListagemEnderecoDTO toListagemEnderecoDTO(Endereco endereco);

}
