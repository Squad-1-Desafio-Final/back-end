package br.com.acabouMony.dto;

import java.math.BigDecimal;

public record CadastroProdutoDto(String nome, BigDecimal preco, String descricao, Byte disponivel, Integer quantidade) {
}
