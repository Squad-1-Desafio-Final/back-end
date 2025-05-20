package br.com.acabouMony.controller;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.dto.ListagemContaDTO;
import br.com.acabouMony.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    public ResponseEntity<ListagemContaDTO> saveConta(CadastroContaDTO dto) {
        try {
            var conta = contaService.saveConta(dto);
            return ResponseEntity.status(201).body(conta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).build();
        }

    }
}
