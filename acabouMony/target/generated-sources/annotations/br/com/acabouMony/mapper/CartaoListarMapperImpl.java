package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.tipos.TipoPagamento;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T13:40:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class CartaoListarMapperImpl implements CartaoListarMapper {

    @Override
    public Cartao toEntity(ListagemCartaoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CadastroCartaoDTO cartaoDTO = null;

        Cartao cartao = new Cartao( cartaoDTO );

        return cartao;
    }

    @Override
    public ListagemCartaoDTO toCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        String numero = null;
        TipoPagamento tipo = null;

        ListagemCartaoDTO listagemCartaoDTO = new ListagemCartaoDTO( numero, tipo );

        return listagemCartaoDTO;
    }

    @Override
    public ListagemCartaoDTO toDadosCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        String numero = null;
        TipoPagamento tipo = null;

        ListagemCartaoDTO listagemCartaoDTO = new ListagemCartaoDTO( numero, tipo );

        return listagemCartaoDTO;
    }
}
