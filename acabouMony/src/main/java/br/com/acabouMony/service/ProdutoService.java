package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.exception.ProdutoNaoEncontrado;
import br.com.acabouMony.mapper.ProdutoMapperStruct;
import br.com.acabouMony.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    ProdutoMapperStruct produtoMapperStruct;

    public CadastroProdutoDto criar(CadastroProdutoDto dados){

        Produto produto1 = produtoMapperStruct.toEntity(dados);
        Produto produtoSalvo = repository.save(produto1);
        return produtoMapperStruct.toProdutoDto(produtoSalvo);
    }

    public List<CadastroProdutoDto> listar(UUID id){
        Optional<Produto> produtos = repository.findById(id);

        return produtos.stream()
                .map(produtoMapperStruct::toProdutoDto)
                .collect(Collectors.toList());

    }
    public void deletar(UUID id){
        repository.deleteById(id);
    }

    public CadastroProdutoDto atualizar(UUID id){
        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNaoEncontrado("Produto não encontrado"));

        Produto produtoSalvo = repository.save(produto);
        return produtoMapperStruct.toProdutoDto(produtoSalvo);


    }
}
