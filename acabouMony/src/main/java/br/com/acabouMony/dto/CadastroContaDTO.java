package br.com.acabouMony.dto;

import java.time.LocalDate;

public record CadastroContaDTO(LocalDate dataVencimento,
                               double limite,
                               int agencia,
                               int numero,
                               int banco) {
}
