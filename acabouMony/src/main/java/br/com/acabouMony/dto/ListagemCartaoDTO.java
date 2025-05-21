package br.com.acabouMony.dto;

import br.com.acabouMony.tipos.TipoPagamento;

public record ListagemCartaoDTO(Long numero, TipoPagamento tipo) {
}
