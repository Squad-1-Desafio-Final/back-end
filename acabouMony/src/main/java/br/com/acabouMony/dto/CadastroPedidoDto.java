package br.com.acabouMony.dto;

import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Usuario;

import java.util.Date;
import java.util.List;

public record CadastroPedidoDto(
        Usuario usuario,
        List<Produto> produtos
) {
}
