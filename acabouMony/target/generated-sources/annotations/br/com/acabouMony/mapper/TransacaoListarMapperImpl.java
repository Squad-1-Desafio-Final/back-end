package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CartaoResumoDto;
import br.com.acabouMony.dto.ListagemTransacaoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Transacao;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T11:40:07-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class TransacaoListarMapperImpl implements TransacaoListarMapper {

    @Override
    public Transacao toEntity(ListagemTransacaoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        return transacao;
    }

    @Override
    public ListagemTransacaoDto toTransacaoDto(Transacao entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioResumoDto usuario = null;
        CartaoResumoDto cartao = null;
        Date date = null;
        Pedido pedido = null;

        ListagemTransacaoDto listagemTransacaoDto = new ListagemTransacaoDto( usuario, cartao, date, pedido );

        return listagemTransacaoDto;
    }
}
