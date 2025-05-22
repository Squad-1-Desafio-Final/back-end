package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoListarMapperStruct {


//  @Mapping(source = "carrinho", target = "carrinho")
    Pedido toEntity(ListagemPedidoDto dto);
    ListagemPedidoDto toPedidoDto(Pedido entity);
    ListagemPedidoDto toDadosPedidoDto(Pedido entity);

}
