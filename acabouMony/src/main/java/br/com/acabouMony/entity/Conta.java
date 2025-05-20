package br.com.acabouMony.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Date dataVencimento;

    private double saldo;

    private double limite;

    private double credito;

    private int agencia;

    private int numero;

    private int banco;

    private Date dataCriacao;

    @OneToOne
    private Usuario usuario;
}
