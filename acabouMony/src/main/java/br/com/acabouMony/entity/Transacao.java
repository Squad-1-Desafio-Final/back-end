package br.com.acabouMony.entity;

import br.com.acabouMony.tipos.TipoPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
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
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private java.util.UUID id;

    @FutureOrPresent
    private Date data;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    @ManyToOne
    private Cartao cartao;

    @ManyToOne
    private Usuario destinatario;

    @ManyToOne
    private Pedido pedido;

}
