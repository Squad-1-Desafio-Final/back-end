package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroUsuarioDTO;
import br.com.acabouMony.dto.ListagemUsuarioDTO;
import br.com.acabouMony.entity.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T11:40:07-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(CadastroUsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CadastroUsuarioDTO usuarioDTO = null;

        Usuario usuario = new Usuario( usuarioDTO );

        return usuario;
    }

    @Override
    public ListagemUsuarioDTO toListagemUsuarioDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String nome = null;
        String email = null;
        String telefone = null;
        Date dtNasc = null;

        ListagemUsuarioDTO listagemUsuarioDTO = new ListagemUsuarioDTO( nome, email, telefone, dtNasc );

        return listagemUsuarioDTO;
    }
}
