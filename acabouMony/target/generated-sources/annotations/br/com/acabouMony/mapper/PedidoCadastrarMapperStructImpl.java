package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T14:40:31-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class PedidoCadastrarMapperStructImpl implements PedidoCadastrarMapperStruct {

    @Override
    public Pedido toEntity(CadastroPedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setUsuario( dto.usuario() );
        List<Produto> list = dto.produtos();
        if ( list != null ) {
            pedido.setProdutos( new ArrayList<Produto>( list ) );
        }

        return pedido;
    }

    @Override
    public CadastroPedidoDto toPedidoDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        Usuario usuario = null;
        List<Produto> produtos = null;

        usuario = entity.getUsuario();
        List<Produto> list = entity.getProdutos();
        if ( list != null ) {
            produtos = new ArrayList<Produto>( list );
        }

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

        usuario = entity.getUsuario();
        List<Produto> list = entity.getProdutos();
        if ( list != null ) {
            produtos = new ArrayList<Produto>( list );
        }

        CadastroPedidoDto cadastroPedidoDto = new CadastroPedidoDto( usuario, produtos );

        return cadastroPedidoDto;
    }
}
