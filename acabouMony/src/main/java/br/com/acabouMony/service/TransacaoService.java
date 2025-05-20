package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroTransacaoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Transacao;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.exception.CartaoNaoEncontrado;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.mapper.TransacaoCadastroMapper;
import br.com.acabouMony.repository.CartaoRepository;
import br.com.acabouMony.repository.ProdutoRepository;
import br.com.acabouMony.repository.TransacaoRepository;
import br.com.acabouMony.repository.UsuarioRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    TransacaoCadastroMapper transacaoCadastroMapper;

    public CadastroTransacaoDto criar (CadastroTransacaoDto dados){

        Usuario usuario = usuarioRepository.findById(dados.usuario().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        Cartao cartao = cartaoRepository.findById(dados.cartao().getId())
                .orElseThrow(() -> new CartaoNaoEncontrado("Cartão não encontrado"));

        Transacao transacao = transacaoCadastroMapper.toEntity(dados);

        Transacao transacaoSalva = repository.save(transacao);

        return transacaoCadastroMapper.toTransacaoDto(transacaoSalva);

    }
    public List<Transacao> listar (){

      return repository.findAll();

    }

}
