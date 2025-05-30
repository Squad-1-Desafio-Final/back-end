package br.com.acabouMony.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CadastroUsuarioDTO(
        @NotBlank
        String nome,
        String email,
        String senha,
        String cpf,
        String telefone,
        Date dtNasc
) {
}
