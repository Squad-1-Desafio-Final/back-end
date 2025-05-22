package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroTransacaoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Transacao;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.tipos.TipoPagamento;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T13:40:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class TransacaoCadastroMapperImpl implements TransacaoCadastroMapper {

    @Override
    public Transacao toEntity(CadastroTransacaoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        return transacao;
    }

    @Override
    public CadastroTransacaoDto toTransacaoDto(Transacao entity) {
        if ( entity == null ) {
            return null;
        }

        TipoPagamento tipo = null;
        Cartao cartao = null;
        Usuario usuario = null;
        Pedido pedido = null;

        CadastroTransacaoDto cadastroTransacaoDto = new CadastroTransacaoDto( tipo, cartao, usuario, pedido );

        return cadastroTransacaoDto;
    }

    @Override
    public CadastroTransacaoDto toDadosTransacaoDto(Transacao entity) {
        if ( entity == null ) {
            return null;
        }

        TipoPagamento tipo = null;
        Cartao cartao = null;
        Usuario usuario = null;
        Pedido pedido = null;

        CadastroTransacaoDto cadastroTransacaoDto = new CadastroTransacaoDto( tipo, cartao, usuario, pedido );

        return cadastroTransacaoDto;
    }
}
