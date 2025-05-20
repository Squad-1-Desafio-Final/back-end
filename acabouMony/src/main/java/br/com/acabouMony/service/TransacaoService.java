package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroTransacaoDto;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.repository.ProdutoRepository;
import br.com.acabouMony.repository.TransacaoRepository;
import br.com.acabouMony.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public CadastroTransacaoDto criar (CadastroTransacaoDto dados){

        Usuario usuario = usuarioRepository.findById(dados.usuario().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
    }

}
