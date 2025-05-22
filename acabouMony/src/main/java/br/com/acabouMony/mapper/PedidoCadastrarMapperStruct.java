package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoCadastrarMapperStruct {

// @Mapping(source = "usuario", target = "usuario")
    Pedido toEntity(CadastroPedidoDto dto);
    CadastroPedidoDto toPedidoDto(Pedido entity);
    CadastroPedidoDto toDadosPedidoDto(Pedido entity);

}
