package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.entity.Pedido;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.exception.CartaoNaoEncontrado;
import br.com.acabouMony.exception.IdNaoEncontradoException;
import br.com.acabouMony.exception.PedidoNaoEncontrado;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.mapper.CartaoListarMapper;
import br.com.acabouMony.mapper.CartaoMapperStruct;
import br.com.acabouMony.repository.CartaoRepository;
import br.com.acabouMony.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository repository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    CartaoMapperStruct cartaoMapperStruct;

    @Autowired
    CartaoListarMapper cartaoListarMapper;



    public CadastroCartaoDTO criar(CadastroCartaoDTO dto){
        Cartao cartao = cartaoMapperStruct.toEntity(dto);
        var conta = contaRepository.findByNumero(dto.numeroConta());


        if (conta.isPresent()){
            throw new RuntimeException("Conta já existente");

        }

        cartao.setConta(conta.get());

        repository.save(cartao);
        System.out.println(cartao.getConta());
        return cartaoMapperStruct.toCartaoDto(cartao);

    }



    public List<ListagemCartaoDTO> listar(){
        var lista = repository.listarNumETipo();

        return lista;
    }


    public ListagemCartaoDTO listarPorId(UUID id){
        Cartao cartao = repository.findById(id) .orElseThrow(() -> new IdNaoEncontradoException("Id não encontrado"));


        return cartaoListarMapper.toCartaoDto(cartao);



    }


    public Cartao editar(UUID id) {
        Cartao cartaoEncontrado = repository.findById(id) .orElseThrow(() -> new CartaoNaoEncontrado("Cartão não encontrado"));

        cartaoEncontrado.setAtivo(false);

        return repository.save(cartaoEncontrado);
    }

}
