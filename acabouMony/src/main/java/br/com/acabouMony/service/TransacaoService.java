package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroTransacaoDto;
import br.com.acabouMony.dto.ListagemTransacaoDto;
import br.com.acabouMony.entity.*;
import br.com.acabouMony.exception.CartaoNaoEncontrado;
import br.com.acabouMony.exception.PedidoNaoEncontrado;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.mapper.TransacaoCadastroMapper;
import br.com.acabouMony.mapper.TransacaoListarMapper;
import br.com.acabouMony.repository.*;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    TransacaoCadastroMapper transacaoCadastroMapper;

    @Autowired
    TransacaoListarMapper transacaoListarMapper;

    public ListagemTransacaoDto criar (CadastroTransacaoDto dados){

        Usuario usuario = usuarioRepository.findById(dados.usuario().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        Cartao cartao = cartaoRepository.findById(dados.cartao().getId())
                .orElseThrow(() -> new CartaoNaoEncontrado("Cartão não encontrado"));

        Pedido pedido = pedidoRepository.findById(dados.pedido().getId())
                .orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));

        Transacao transacao = transacaoCadastroMapper.toEntity(dados);
        transacao.setData(new Date(System.currentTimeMillis() + 1000));
        transacao.setCartao(cartao);
        transacao.setPedido(pedido);
        transacao.setDestinatario(usuario);
        transacao.setTipo(dados.tipo());
        Transacao transacaoSalva = repository.save(transacao);

        return transacaoListarMapper.toTransacaoDto(transacaoSalva);

    }

    public List<ListagemTransacaoDto> listar (){

      List<Transacao> transacoes = repository.findAll();

        return transacoes.stream()
                .map(transacaoListarMapper::toTransacaoDto)
                .collect(Collectors.toList());


    }

}
