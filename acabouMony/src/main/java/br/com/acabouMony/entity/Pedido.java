package br.com.acabouMony.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.UUID;

import java.util.Date;

@Entity
public class Pedido {

    @UUID
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //Usurio
    //Produto

    @PositiveOrZero
    private Double precoTotal;

    @FutureOrPresent
    private Date date;

    private Boolean carrinho;
}
