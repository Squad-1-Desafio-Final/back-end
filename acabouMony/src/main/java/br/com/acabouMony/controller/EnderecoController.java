package br.com.acabouMony.controller;


import br.com.acabouMony.dto.CadastroEnderecoDTO;
import br.com.acabouMony.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<String> cadastrarEndereco(@RequestBody @Valid CadastroEnderecoDTO enderecoDTO) {
            enderecoService.saveEndereco(enderecoDTO);
            return ResponseEntity.status(201).body("Cadastro de endereço feito com sucesso!");
    }
}
