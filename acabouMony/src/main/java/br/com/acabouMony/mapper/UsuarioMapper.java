package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroUsuarioDTO;
import br.com.acabouMony.dto.ListagemUsuarioDTO;
import br.com.acabouMony.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
//    @Mapping(source = "nome", target = "nome")
    Usuario toEntity(CadastroUsuarioDTO dto);
    ListagemUsuarioDTO toListagemUsuarioDTO(Usuario usuario);
}
