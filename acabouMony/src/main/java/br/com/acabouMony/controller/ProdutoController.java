package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService service;


    @PostMapping("/criar")
    public ResponseEntity<CadastroProdutoDto> criandoProduto(@RequestBody CadastroProdutoDto dados ){
        return ResponseEntity.status(201).body(service.criar(dados));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<CadastroProdutoDto>> listarProduto(@PathVariable UUID id){
        return ResponseEntity.status(200).body(service.listar(id));
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<CadastroProdutoDto> atualizarProduto(@PathVariable UUID id){
        return ResponseEntity.status(200).body(service.atualizar(id));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CadastroProdutoDto>> listarTodos(){
        return ResponseEntity.status(200).build();

    }



}
