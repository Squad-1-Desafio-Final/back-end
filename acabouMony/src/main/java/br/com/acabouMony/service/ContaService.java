package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.dto.ListagemContaDTO;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.mapper.ContaMapper;
import br.com.acabouMony.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaMapper contaMapper;

    public ListagemContaDTO saveConta(CadastroContaDTO dto) {

        Conta conta = contaMapper.toEntity(dto);
        var dataAtual = LocalDate.now();
        conta.setDataCriacao(
                new Date(dataAtual.getYear(), dataAtual.getMonthValue(), dataAtual.getDayOfMonth()));

        contaRepository.save(conta);
        return contaMapper.toListagemContaDTO(conta);
    }
}
