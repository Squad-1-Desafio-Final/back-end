package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.dto.ListagemPedidoDto;
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
    public ResponseEntity<List<ListagemPedidoDto>> listar(){
        return ResponseEntity.status(200).body(service.listar());
    }

    @PostMapping
    public ResponseEntity<ListagemPedidoDto> criar(@RequestBody CadastroPedidoDto dados){
        return ResponseEntity.status(201).body(service.criar(dados));
    }

    @PatchMapping("concluir-transacao/{id}")
    public ResponseEntity<ListagemPedidoDto> concluirTransacao(@PathVariable  UUID id){
        return ResponseEntity.status(200).body(service.concluirTransacao(id));
    }

    @PatchMapping("/editar/{id}")
    public ResponseEntity<ListagemPedidoDto> editar(@PathVariable UUID id, @RequestBody Pedido dados){
        return ResponseEntity.status(200).body(service.editar(id, dados));
    }

}
