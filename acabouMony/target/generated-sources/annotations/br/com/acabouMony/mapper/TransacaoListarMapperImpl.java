package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CartaoResumoDto;
import br.com.acabouMony.dto.ListagemTransacaoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Transacao;
import br.com.acabouMony.tipos.TipoPagamento;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T15:02:44-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class TransacaoListarMapperImpl implements TransacaoListarMapper {

    @Override
    public Transacao toEntity(ListagemTransacaoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        transacao.setCartao( cartaoResumoDtoToCartao( dto.cartao() ) );
        transacao.setPedido( dto.pedido() );

        return transacao;
    }

    @Override
    public ListagemTransacaoDto toTransacaoDto(Transacao entity) {
        if ( entity == null ) {
            return null;
        }

        CartaoResumoDto cartao = null;
        Pedido pedido = null;

        cartao = cartaoToCartaoResumoDto( entity.getCartao() );
        pedido = entity.getPedido();

        UsuarioResumoDto usuario = null;
        Date date = null;

        ListagemTransacaoDto listagemTransacaoDto = new ListagemTransacaoDto( usuario, cartao, date, pedido );

        return listagemTransacaoDto;
    }

    protected Cartao cartaoResumoDtoToCartao(CartaoResumoDto cartaoResumoDto) {
        if ( cartaoResumoDto == null ) {
            return null;
        }

        Cartao cartao = new Cartao();

        cartao.setNumero( (long) cartaoResumoDto.numero() );
        if ( cartaoResumoDto.tipo() != null ) {
            cartao.setTipo( Enum.valueOf( TipoPagamento.class, cartaoResumoDto.tipo() ) );
        }

        return cartao;
    }

    protected CartaoResumoDto cartaoToCartaoResumoDto(Cartao cartao) {
        if ( cartao == null ) {
            return null;
        }

        int numero = 0;
        String tipo = null;

        if ( cartao.getNumero() != null ) {
            numero = cartao.getNumero().intValue();
        }
        if ( cartao.getTipo() != null ) {
            tipo = cartao.getTipo().name();
        }

        CartaoResumoDto cartaoResumoDto = new CartaoResumoDto( numero, tipo );

        return cartaoResumoDto;
    }
}
