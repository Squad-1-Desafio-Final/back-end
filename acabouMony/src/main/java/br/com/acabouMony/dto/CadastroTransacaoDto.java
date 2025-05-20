package br.com.acabouMony.dto;

import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.tipos.TipoPagamento;

import java.util.Date;

public record CadastroTransacaoDto(
        Date data,
        TipoPagamento tipo,
        Cartao cartao,
        Usuario usuario
) { }
