package br.com.acabouMony.entity;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.tipos.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private java.util.UUID id;

    private String numero;

    private Integer cvv;

    private Integer senha;

    private String validade;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    @ManyToOne
    private Conta conta;

    private Boolean ativo;


    public Cartao(CadastroCartaoDTO cartaoDTO) {

    }

    public boolean isAtivo() {
        return false;
    }
}
