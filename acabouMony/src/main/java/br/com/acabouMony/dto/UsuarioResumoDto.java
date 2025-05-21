package br.com.acabouMony.dto;

import java.util.UUID;

public record UsuarioResumoDto(
        UUID id,
        String nome,
        String email
) {
}
