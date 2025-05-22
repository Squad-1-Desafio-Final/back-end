package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.service.CartaoService;
import br.com.acabouMony.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    CartaoService service;

    @PostMapping("/criar")
    public ResponseEntity<CadastroCartaoDTO> criarcartao(@RequestBody CadastroCartaoDTO dados){
        return ResponseEntity.status(201).body(service.criar(dados));

    }

    @GetMapping("/listar")
    public ResponseEntity<List<ListagemCartaoDTO>> listarCartao(){

        return ResponseEntity.status(200).body(service.listar());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<ListagemCartaoDTO> listarPorId(@PathVariable UUID id){

        return ResponseEntity.status(200).body(service.listarPorId(id));
    }

    @PatchMapping("/desativar/{id}")
    public ResponseEntity<Cartao> editar(@PathVariable UUID id){
        return ResponseEntity.status(200).body(service.editar(id));
    }







}
