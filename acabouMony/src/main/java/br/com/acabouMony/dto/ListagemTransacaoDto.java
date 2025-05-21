package br.com.acabouMony.dto;

import br.com.acabouMony.entity.Pedido;

import java.util.Date;

public record ListagemTransacaoDto(
        UsuarioResumoDto usuario,
        CartaoResumoDto cartao,
        Date date,
        Pedido pedido
) {
}
