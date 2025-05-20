package br.com.acabouMony.dto;

import java.util.Date;

public record CadastroContaDTO(Date dataVencimento,
                               double saldo,
                               double limite,
                               int agencia,
                               int numero,
                               int banco) {
}
