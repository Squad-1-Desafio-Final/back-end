package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    ProdutoService service;

//    @PostMapping("/criar")
//    public ResponseEntity<CadastroCartaoDTO> criarcartao(@RequestBody CadastroCartaoDTO dados){
//        return ResponseEntity.status(201).body(service.)
//    }
}
