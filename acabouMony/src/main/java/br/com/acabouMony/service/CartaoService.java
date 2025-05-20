package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.mapper.CartaoMapperStruct;
import br.com.acabouMony.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository repository;

    @Autowired
    CartaoMapperStruct cartaoMapperStruct;

//    public CadastroCartaoDTO criar(){
//
//    }
}
