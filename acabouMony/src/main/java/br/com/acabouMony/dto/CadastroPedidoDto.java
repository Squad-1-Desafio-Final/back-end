package br.com.acabouMony.dto;

import java.util.Date;

public record CadastroPedidoDto(
        Double precoTotal,
        Date date,
        Boolean carrinho
) {
}
