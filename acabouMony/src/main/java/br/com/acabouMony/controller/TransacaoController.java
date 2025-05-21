package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroTransacaoDto;
import br.com.acabouMony.dto.ListagemTransacaoDto;
import br.com.acabouMony.entity.Transacao;
import br.com.acabouMony.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {


    @Autowired
    TransacaoService service;

    @GetMapping
    public ResponseEntity<List<ListagemTransacaoDto>> listar(){
        return ResponseEntity.status(200).body(service.listar());
    }

    @PostMapping
    public ResponseEntity<ListagemTransacaoDto> cadastrar(@RequestBody CadastroTransacaoDto dados){
        return ResponseEntity.status(201).body(service.criar(dados));
    }

}
