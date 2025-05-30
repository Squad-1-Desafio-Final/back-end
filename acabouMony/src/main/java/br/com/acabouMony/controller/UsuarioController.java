package br.com.acabouMony.controller;

import br.com.acabouMony.dto.*;
import br.com.acabouMony.entity.Endereco;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(409).body("Ocorreu um erro ao cadastrar usuário! Já existe um cadastro com esse email!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        var listarUsuarios = usuarioService.listarTodos();

        if (listarUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(listarUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarUmUsuario(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(usuarioService.listarUmUsuario(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/nome")
    public ResponseEntity<Usuario> atualizarNome(@PathVariable UUID id, @RequestBody @Valid AtualizarNomeUsuarioDTO nomeDTO) {
        try {
            var usuario = usuarioService.listarUmUsuario(id);
            usuarioService.atualizarNome(id, nomeDTO.nome());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<Usuario> atualizarEmail(@PathVariable UUID id, @RequestBody @Valid AtualizarEmailUsuarioDTO emailDTO) {
        try {
            var usuario = usuarioService.listarUmUsuario(id);
            usuarioService.atualizarEmail(id, emailDTO.email());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Usuario> atualizarSenha(@PathVariable UUID id, @RequestBody @Valid AtualizarSenhaUsuarioDTO senhaDTO) {
        try {
            var usuario = usuarioService.listarUmUsuario(id);
            usuarioService.atualizarSenha(id, senhaDTO.senha());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/telefone")
    public ResponseEntity<Usuario> atualizarTelefone(@PathVariable UUID id, @RequestBody @Valid AtualizarTelefoneUsuarioDTO telefoneDTO) {
        try {
            var usuario = usuarioService.listarUmUsuario(id);
            usuarioService.atualizarTelefone(id, telefoneDTO.telefone());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
