package br.com.acabouMony.entity;

import br.com.acabouMony.tipos.TipoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import org.hibernate.validator.constraints.UUID;

import java.util.Date;

@Entity
public class Transacao {

    @UUID
    private java.util.UUID id;

    @FutureOrPresent
    private Date data;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    //cartao
    //destinatario
    //pedido

}
