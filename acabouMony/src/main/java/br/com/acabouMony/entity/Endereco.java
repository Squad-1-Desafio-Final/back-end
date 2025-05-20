package br.com.acabouMony.entity;

import br.com.acabouMony.dto.CadastroEnderecoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco {

    @Id
    private UUID id;

    private String logradouro;

    private int numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    @OneToOne
    private Usuario usuario;


    public Endereco(CadastroEnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
        this.bairro = enderecoDTO.bairro();
        this.cidade = enderecoDTO.cidade();
        this.estado = enderecoDTO.estado();
        this.cep = enderecoDTO.cep();
    }
}
