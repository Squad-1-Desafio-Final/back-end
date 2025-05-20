package br.com.acabouMony.dto;

import java.util.Date;

public record ListagemContaDTO(Date dataVencimento,
                               double limite,
                               int agencia,
                               int numero) {
}
