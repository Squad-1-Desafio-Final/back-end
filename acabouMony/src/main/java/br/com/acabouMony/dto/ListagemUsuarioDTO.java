package br.com.acabouMony.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record ListagemUsuarioDTO(
        String nome,
        String email,
        String telefone,
        Date dtNasc
) {
}
