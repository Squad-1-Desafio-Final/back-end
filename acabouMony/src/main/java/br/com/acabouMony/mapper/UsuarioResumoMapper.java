package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Usuario;
import org.mapstruct.Mapping;

public interface UsuarioResumoMapper {

//    @Mapping(source = "nome", target = "nome")
    Usuario toEntity(UsuarioResumoDto dto);
    UsuarioResumoDto toPedidoDto(Usuario entity);
    UsuarioResumoDto toDadosPedidoDto(Usuario entity);


}
