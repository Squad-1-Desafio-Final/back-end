package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CartaoResumoDto;
import br.com.acabouMony.entity.Cartao;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T11:40:07-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class CartaoResumoMapperImpl implements CartaoResumoMapper {

    @Override
    public Cartao toEntity(CartaoResumoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cartao cartao = new Cartao();

        return cartao;
    }

    @Override
    public CartaoResumoDto toTransacaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        int numero = 0;
        String tipo = null;

        CartaoResumoDto cartaoResumoDto = new CartaoResumoDto( numero, tipo );

        return cartaoResumoDto;
    }
}
