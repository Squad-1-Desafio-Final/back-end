package br.com.acabouMony.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastroContaDTO(LocalDate dataVencimento,
                               @NotNull double limite,
                               @NotNull int agencia,
                               @NotNull int numero,
                               @NotNull int banco,
                               @NotBlank String cpf) {
}
