package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CartaoResumoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.tipos.TipoPagamento;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T15:52:11-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class CartaoResumoMapperImpl implements CartaoResumoMapper {

    @Override
    public Cartao toEntity(CartaoResumoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cartao cartao = new Cartao();

        cartao.setNumero( (long) dto.numero() );
        if ( dto.tipo() != null ) {
            cartao.setTipo( Enum.valueOf( TipoPagamento.class, dto.tipo() ) );
        }

        return cartao;
    }

    @Override
    public CartaoResumoDto toTransacaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        int numero = 0;
        String tipo = null;

        if ( entity.getNumero() != null ) {
            numero = entity.getNumero().intValue();
        }
        if ( entity.getTipo() != null ) {
            tipo = entity.getTipo().name();
        }

        CartaoResumoDto cartaoResumoDto = new CartaoResumoDto( numero, tipo );

        return cartaoResumoDto;
    }
}
