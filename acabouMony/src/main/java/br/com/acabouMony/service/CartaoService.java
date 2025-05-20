package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.mapper.CartaoMapperStruct;
import br.com.acabouMony.repository.CartaoRepository;
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
    CartaoMapperStruct cartaoMapperStruct;

    public CadastroCartaoDTO criar(CadastroCartaoDTO dto){
        Cartao cartao = cartaoMapperStruct.toEntity(dto);
        Cartao cartaoSalvo = repository.save(cartao);
        return cartaoMapperStruct.toCartaoDto(cartaoSalvo);

    }

    public List<CadastroCartaoDTO> listar(){
        List<Cartao> lista = repository.listarNumETipo();

        return lista.stream()
                .map(cartaoMapperStruct::toCartaoDto)
                .collect(Collectors.toList());
    }

    public List<CadastroCartaoDTO> listarPorId(UUID id){
        Optional<Cartao> cartao = repository.findById(id);

        return cartao.stream()
                .map(cartaoMapperStruct::toCartaoDto)
                .collect(Collectors.toList());
    }








}
