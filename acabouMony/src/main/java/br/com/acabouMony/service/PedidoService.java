package br.com.acabouMony.service;

import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.exception.PedidoNaoEncontrado;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.repository.PedidoRepository;
import br.com.acabouMony.repository.UsuarioRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Pedido> listar(){
        return repository.findAll();
    }

    public Pedido criar(Pedido dados){

        Usuario usuario = usuarioRepository.findById(dados.getUsuario().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        return repository.save(dados);

    }

    public Pedido editar(UUID id, Pedido dados){

        Pedido pedidoEncontrado = repository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));


        pedidoEncontrado.setProdutos(dados.getProdutos());

        return repository.save(pedidoEncontrado);

    }

    public Pedido concluirTransacao(UUID id){

        Pedido pedidoEncontrado = repository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));

        pedidoEncontrado.setCarrinho(false);

        return repository.save(pedidoEncontrado);

    }

    public void deletar(UUID id){

        if (!repository.existsById(id)){
            throw new PedidoNaoEncontrado("Pedido não econtrado");
        }

         repository.deleteById(id);

    }

}
