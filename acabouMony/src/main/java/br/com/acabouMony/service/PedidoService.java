package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroPedidoDto;
import br.com.acabouMony.dto.ListagemPedidoDto;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.exception.PedidoNaoEncontrado;
import br.com.acabouMony.exception.PedidoNaoPodeSerEditadoException;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.mapper.PedidoCadastrarMapperStruct;
import br.com.acabouMony.mapper.PedidoListarMapperStruct;
import br.com.acabouMony.repository.PedidoRepository;
import br.com.acabouMony.repository.ProdutoRepository;
import br.com.acabouMony.repository.UsuarioRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    PedidoCadastrarMapperStruct pedidoCadastrarMapperStruct;

    @Autowired
    PedidoListarMapperStruct pedidoListarMapperStruct;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public List<ListagemPedidoDto> listar(){
        List<Pedido> pedidos = repository.findAll();

        return pedidos.stream()
                .map(pedidoListarMapperStruct::toPedidoDto)
                .collect(Collectors.toList());

    }

    public ListagemPedidoDto criar(CadastroPedidoDto dados){

        Usuario usuario = usuarioRepository.findById(dados.usuario().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        List<UUID> ids = dados.produtos().stream()
                .map(Produto::getId)
                .collect(Collectors.toList());

        List<Produto> produtosEncontrados = produtoRepository.findAllById(ids);

// Criar um Map para acessar rapidamente Produto pelo ID
        Map<UUID, Produto> produtoMap = produtosEncontrados.stream()
                .collect(Collectors.toMap(Produto::getId, Function.identity()));

// Reconstituir a lista de produtos mantendo repetições
        List<Produto> produtos = ids.stream()
                .map(produtoMap::get)
                .collect(Collectors.toList());


        Pedido pedido = pedidoCadastrarMapperStruct.toEntity(dados);


        pedido.setDate(new Date(System.currentTimeMillis() + 1000));
        pedido.setCarrinho(true);
        pedido.setUsuario(usuario);
        pedido.setProdutos(produtos);

        BigDecimal precoTotal = pedido.getProdutos()
                .stream()
                .map(Produto::getPreco)
                .filter(Objects::nonNull)  // Evita null na soma
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        double precoTotalDouble = precoTotal.doubleValue();

        pedido.setPrecoTotal(precoTotalDouble);




        Pedido pedidoSalvo = repository.save(pedido);

        return pedidoListarMapperStruct.toPedidoDto(pedidoSalvo);

    }

    public ListagemPedidoDto editar(UUID id, Pedido dados){

        Pedido pedidoEncontrado = repository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));

        if(!pedidoEncontrado.getCarrinho()){
            throw new PedidoNaoPodeSerEditadoException("O pedido não pode ser editado porque está em uma transação");
        }

        pedidoEncontrado.setProdutos(dados.getProdutos());
        Pedido pedidoSavo = repository.save(pedidoEncontrado);

        return pedidoListarMapperStruct.toPedidoDto(pedidoSavo);

    }

    public ListagemPedidoDto concluirTransacao(UUID id){

        Pedido pedidoEncontrado = repository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontrado("Pedido não encontrado"));

        pedidoEncontrado.setCarrinho(false);

        Pedido pedidoSavo = repository.save(pedidoEncontrado);

        return pedidoListarMapperStruct.toPedidoDto(pedidoSavo);
    }

}
