package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Produto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T13:40:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class PedidoListarMapperStructImpl implements PedidoListarMapperStruct {

    @Override
    public Pedido toEntity(ListagemPedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        return pedido;
    }

    @Override
    public ListagemPedidoDto toPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        UsuarioResumoDto usuario = null;
        List<Produto> produtos = null;
        double precoTotal = 0.0d;
        LocalDateTime date = null;
        boolean carrinho = false;

        ListagemPedidoDto listagemPedidoDto = new ListagemPedidoDto( id, usuario, produtos, precoTotal, date, carrinho );

        return listagemPedidoDto;
    }

    @Override
    public ListagemPedidoDto toDadosPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        UsuarioResumoDto usuario = null;
        List<Produto> produtos = null;
        double precoTotal = 0.0d;
        LocalDateTime date = null;
        boolean carrinho = false;

        ListagemPedidoDto listagemPedidoDto = new ListagemPedidoDto( id, usuario, produtos, precoTotal, date, carrinho );

        return listagemPedidoDto;
    }
}
