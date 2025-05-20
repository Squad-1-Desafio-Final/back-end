package br.com.acabouMony.entity;

import br.com.acabouMony.tipos.TipoPagamento;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.UUID;

import java.util.Date;

@Entity
public class Cartao {

    @UUID
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Long numero;

    private Integer cvv;

    private Integer senha;

    private Date validade;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    @ManyToOne
    private Conta conta;




}
