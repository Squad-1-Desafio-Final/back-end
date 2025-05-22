package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoListarMapperStruct {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "usuario", target = "usuario")
  @Mapping(source = "produtos", target = "produtos")
  @Mapping(source = "precoTotal", target = "precoTotal")
  @Mapping(source = "date", target = "date")
  @Mapping(source = "carrinho", target = "carrinho")
    Pedido toEntity(ListagemPedidoDto dto);
    ListagemPedidoDto toPedidoDto(Pedido entity);
    ListagemPedidoDto toDadosPedidoDto(Pedido entity);

}
