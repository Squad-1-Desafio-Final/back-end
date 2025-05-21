package br.com.acabouMony.dto;

import br.com.acabouMony.entity.Produto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ListagemPedidoDto(
        UUID id,
        UsuarioResumoDto usuario,
        List<Produto> produtos,
        double precoTotal,
        LocalDateTime date,
        boolean carrinho
){}
