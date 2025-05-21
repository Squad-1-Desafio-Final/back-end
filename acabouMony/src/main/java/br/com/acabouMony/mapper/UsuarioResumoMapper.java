package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Usuario;

public interface UsuarioResumoMapper {

//        @Mapping(source = "usuario", target = "usuario")
    Usuario toEntity(UsuarioResumoDto dto);
    UsuarioResumoDto toPedidoDto(Usuario entity);
    UsuarioResumoDto toDadosPedidoDto(Usuario entity);


}
