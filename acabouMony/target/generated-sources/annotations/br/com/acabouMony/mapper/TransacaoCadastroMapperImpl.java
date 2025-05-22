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
    date = "2025-05-22T09:43:36-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class TransacaoCadastroMapperImpl implements TransacaoCadastroMapper {

    @Override
    public Transacao toEntity(CadastroTransacaoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        transacao.setTipo( dto.tipo() );
        transacao.setCartao( dto.cartao() );
        transacao.setPedido( dto.pedido() );

        return transacao;
    }

    @Override
    public CadastroTransacaoDto toTransacaoDto(Transacao entity) {
        if ( entity == null ) {
            return null;
        }

        TipoPagamento tipo = null;
        Cartao cartao = null;
        Pedido pedido = null;

        tipo = entity.getTipo();
        cartao = entity.getCartao();
        pedido = entity.getPedido();

        Usuario usuario = null;

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
        Pedido pedido = null;

        tipo = entity.getTipo();
        cartao = entity.getCartao();
        pedido = entity.getPedido();

        Usuario usuario = null;

        CadastroTransacaoDto cadastroTransacaoDto = new CadastroTransacaoDto( tipo, cartao, usuario, pedido );

        return cadastroTransacaoDto;
    }
}
