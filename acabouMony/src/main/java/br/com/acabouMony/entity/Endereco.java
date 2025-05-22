package br.com.acabouMony.entity;

import br.com.acabouMony.dto.CadastroEnderecoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    // ✅ Required no-args constructor
    public Endereco() {}

    public Endereco(CadastroEnderecoDTO dto) {
        this.id = UUID.randomUUID(); // Or set externally
        this.logradouro = dto.logradouro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
    }

    public Endereco(UUID id, String logradouro, int numero, String complemento,
                    String bairro, String cidade, String estado, String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
}
