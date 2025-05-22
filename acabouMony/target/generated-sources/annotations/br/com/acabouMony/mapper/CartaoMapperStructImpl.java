package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroCartaoDTO;
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
public class CartaoMapperStructImpl implements CartaoMapperStruct {

    @Override
    public Cartao toEntity(CadastroCartaoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CadastroCartaoDTO cartaoDTO = null;

        Cartao cartao = new Cartao( cartaoDTO );

        return cartao;
    }

    @Override
    public CadastroCartaoDTO toCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        Integer senha = null;
        TipoPagamento tipo = null;
        int numeroConta = 0;

        CadastroCartaoDTO cadastroCartaoDTO = new CadastroCartaoDTO( senha, tipo, numeroConta );

        return cadastroCartaoDTO;
    }

    @Override
    public CadastroCartaoDTO toDadosCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        Integer senha = null;
        TipoPagamento tipo = null;
        int numeroConta = 0;

        CadastroCartaoDTO cadastroCartaoDTO = new CadastroCartaoDTO( senha, tipo, numeroConta );

        return cadastroCartaoDTO;
    }
}
