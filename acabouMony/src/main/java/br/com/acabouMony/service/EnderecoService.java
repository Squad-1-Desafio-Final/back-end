package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroEnderecoDTO;
import br.com.acabouMony.dto.ListagemEnderecoDTO;
import br.com.acabouMony.entity.Endereco;
import br.com.acabouMony.mapper.EnderecoMapper;
import br.com.acabouMony.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Transactional
    public ListagemEnderecoDTO saveEndereco(CadastroEnderecoDTO enderecoDTO) {

        var endereco = new Endereco(enderecoDTO);
        enderecoRepository.save(endereco);
        return enderecoMapper.toListagemEnderecoDTO(endereco);
    }

}
