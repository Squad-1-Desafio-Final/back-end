package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.dto.ListagemTransacaoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Transacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoListarMapper {
    Transacao toEntity(ListagemTransacaoDto dto);
    ListagemTransacaoDto toTransacaoDto(Transacao entity);

}
