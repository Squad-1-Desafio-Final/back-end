package br.com.acabouMony.entity;

import br.com.acabouMony.dto.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private String telefone;

    private Date dtNasc;

    public Usuario(CadastroUsuarioDTO usuarioDTO) {
        this.nome = usuarioDTO.nome();
        this.email = usuarioDTO.email();
        this.senha = usuarioDTO.senha();
        this.cpf = usuarioDTO.cpf();
        this.telefone = usuarioDTO.telefone();
        this.dtNasc = usuarioDTO.dtNasc();
    }
}
