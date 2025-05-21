package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.tipos.TipoPagamento;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T14:40:32-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class CartaoListarMapperImpl implements CartaoListarMapper {

    @Override
    public Cartao toEntity(ListagemCartaoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cartao cartao = new Cartao();

        cartao.setNumero( dto.numero() );
        cartao.setTipo( dto.tipo() );

        return cartao;
    }

    @Override
    public ListagemCartaoDTO toCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        Long numero = null;
        TipoPagamento tipo = null;

        numero = entity.getNumero();
        tipo = entity.getTipo();

        ListagemCartaoDTO listagemCartaoDTO = new ListagemCartaoDTO( numero, tipo );

        return listagemCartaoDTO;
    }

    @Override
    public ListagemCartaoDTO toDadosCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        Long numero = null;
        TipoPagamento tipo = null;

        numero = entity.getNumero();
        tipo = entity.getTipo();

        ListagemCartaoDTO listagemCartaoDTO = new ListagemCartaoDTO( numero, tipo );

        return listagemCartaoDTO;
    }
}
