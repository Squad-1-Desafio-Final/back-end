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
    private java.util.UUID id;

    //Usurio
    //Produto

    @PositiveOrZero
    private Double precoTotal;

    @FutureOrPresent
    private Date date;

    private Boolean carrinho;

    public Pedido() {
    }

    public Pedido(Double precoTotal, Date date, Boolean carrinho) {
        this.precoTotal = precoTotal;
        this.date = date;
        this.carrinho = carrinho;
    }

    public java.util.UUID getId() {
        return id;
    }

    public void setId(java.util.UUID id) {
        this.id = id;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Boolean getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Boolean carrinho) {
        this.carrinho = carrinho;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
