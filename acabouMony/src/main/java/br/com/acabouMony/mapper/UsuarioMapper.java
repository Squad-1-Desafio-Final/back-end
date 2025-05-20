package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroUsuarioDTO;
import br.com.acabouMony.dto.ListagemUsuarioDTO;
import br.com.acabouMony.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(CadastroUsuarioDTO dto);
    ListagemUsuarioDTO toListagemUsuarioDTO(Usuario usuario);
}
