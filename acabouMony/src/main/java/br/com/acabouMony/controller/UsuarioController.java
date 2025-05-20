package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroUsuarioDTO;
import br.com.acabouMony.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid CadastroUsuarioDTO usuarioDTO) {
        try{
            usuarioService.saveUsuario(usuarioDTO);
            return ResponseEntity.status(201).body("Cadastro de usuário feito com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body("Ocorreu um erro ao cadastrar usuário! Já existe um cadastro com esse email!");
        }
    }

}
