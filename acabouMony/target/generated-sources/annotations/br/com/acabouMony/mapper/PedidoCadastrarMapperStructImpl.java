package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Usuario;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T13:40:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class PedidoCadastrarMapperStructImpl implements PedidoCadastrarMapperStruct {

    @Override
    public Pedido toEntity(CadastroPedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        return pedido;
    }

    @Override
    public CadastroPedidoDto toPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        Usuario usuario = null;
        List<Produto> produtos = null;

        CadastroPedidoDto cadastroPedidoDto = new CadastroPedidoDto( usuario, produtos );

        return cadastroPedidoDto;
    }

    @Override
    public CadastroPedidoDto toDadosPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        Usuario usuario = null;
        List<Produto> produtos = null;

        CadastroPedidoDto cadastroPedidoDto = new CadastroPedidoDto( usuario, produtos );

        return cadastroPedidoDto;
    }
}
