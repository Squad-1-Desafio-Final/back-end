package br.com.acabouMony.dto;

import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.tipos.TipoPagamento;

import java.util.Date;

public record CadastroCartaoDTO(Long numero, Integer cvv, Integer senha, Date validade, TipoPagamento tipo) {
}
