package br.com.acabouMony.controller;

import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService service;

    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        return ResponseEntity.status(200).body(service.listar());
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido dados){
        return ResponseEntity.status(201).body(service.criar(dados));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> concluirTransacao(UUID id){
        return ResponseEntity.status(200).body(service.concluirTransacao(id));
    }

    @PatchMapping("/editar/{id}")
    public ResponseEntity<Pedido> editar(UUID id, Pedido dados){
        return ResponseEntity.status(200).body(service.editar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Deletar(UUID id, Pedido dados){
        service.deletar(id);
        return ResponseEntity.status(200).build();
    }

}
