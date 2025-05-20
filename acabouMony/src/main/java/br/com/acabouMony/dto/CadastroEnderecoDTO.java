package br.com.acabouMony.dto;

public record CadastroEnderecoDTO(
        String email,
        String logradouro,
        int numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
