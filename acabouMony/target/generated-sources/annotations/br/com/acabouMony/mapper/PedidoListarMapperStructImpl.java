package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Usuario;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T13:24:04-0300",
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

        pedido.setId( dto.id() );
        pedido.setUsuario( usuarioResumoDtoToUsuario( dto.usuario() ) );
        List<Produto> list = dto.produtos();
        if ( list != null ) {
            pedido.setProdutos( new ArrayList<Produto>( list ) );
        }
        pedido.setPrecoTotal( dto.precoTotal() );
        if ( dto.date() != null ) {
            pedido.setDate( Date.from( dto.date().toInstant( ZoneOffset.UTC ) ) );
        }
        pedido.setCarrinho( dto.carrinho() );

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

        id = entity.getId();
        usuario = usuarioToUsuarioResumoDto( entity.getUsuario() );
        List<Produto> list = entity.getProdutos();
        if ( list != null ) {
            produtos = new ArrayList<Produto>( list );
        }
        if ( entity.getPrecoTotal() != null ) {
            precoTotal = entity.getPrecoTotal();
        }
        if ( entity.getDate() != null ) {
            date = LocalDateTime.ofInstant( entity.getDate().toInstant(), ZoneId.of( "UTC" ) );
        }
        if ( entity.getCarrinho() != null ) {
            carrinho = entity.getCarrinho();
        }

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

        id = entity.getId();
        usuario = usuarioToUsuarioResumoDto( entity.getUsuario() );
        List<Produto> list = entity.getProdutos();
        if ( list != null ) {
            produtos = new ArrayList<Produto>( list );
        }
        if ( entity.getPrecoTotal() != null ) {
            precoTotal = entity.getPrecoTotal();
        }
        if ( entity.getDate() != null ) {
            date = LocalDateTime.ofInstant( entity.getDate().toInstant(), ZoneId.of( "UTC" ) );
        }
        if ( entity.getCarrinho() != null ) {
            carrinho = entity.getCarrinho();
        }

        ListagemPedidoDto listagemPedidoDto = new ListagemPedidoDto( id, usuario, produtos, precoTotal, date, carrinho );

        return listagemPedidoDto;
    }

    protected Usuario usuarioResumoDtoToUsuario(UsuarioResumoDto usuarioResumoDto) {
        if ( usuarioResumoDto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( usuarioResumoDto.id() );
        usuario.setNome( usuarioResumoDto.nome() );
        usuario.setEmail( usuarioResumoDto.email() );

        return usuario;
    }

    protected UsuarioResumoDto usuarioToUsuarioResumoDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        String email = null;

        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();

        UsuarioResumoDto usuarioResumoDto = new UsuarioResumoDto( id, nome, email );

        return usuarioResumoDto;
    }
}
