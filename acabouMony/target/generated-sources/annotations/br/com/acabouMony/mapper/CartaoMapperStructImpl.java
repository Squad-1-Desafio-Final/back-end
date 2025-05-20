package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.tipos.TipoPagamento;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T15:05:14-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class CartaoMapperStructImpl implements CartaoMapperStruct {

    @Override
    public Cartao toEntity(CadastroCartaoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cartao cartao = new Cartao();

        return cartao;
    }

    @Override
    public CadastroCartaoDTO toCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        Long numero = null;
        Integer cvv = null;
        Integer senha = null;
        Date validade = null;
        TipoPagamento tipo = null;
        Conta conta = null;

        CadastroCartaoDTO cadastroCartaoDTO = new CadastroCartaoDTO( numero, cvv, senha, validade, tipo, conta );

        return cadastroCartaoDTO;
    }

    @Override
    public CadastroCartaoDTO toDadosCartaoDto(Cartao entity) {
        if ( entity == null ) {
            return null;
        }

        Long numero = null;
        Integer cvv = null;
        Integer senha = null;
        Date validade = null;
        TipoPagamento tipo = null;
        Conta conta = null;

        CadastroCartaoDTO cadastroCartaoDTO = new CadastroCartaoDTO( numero, cvv, senha, validade, tipo, conta );

        return cadastroCartaoDTO;
    }
}
