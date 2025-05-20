package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService service;


//    @PostMapping("/criar")
//    public ResponseEntity<CadastroProdutoDto> criandoProduto(@RequestBody CadastroProdutoDto dados ){
//        return ResponseEntity.status(201).body()
//    }
//

}
