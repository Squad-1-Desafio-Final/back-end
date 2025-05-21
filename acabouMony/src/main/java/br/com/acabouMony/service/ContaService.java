package br.com.acabouMony.service;

import br.com.acabouMony.dto.AtualizacaoContaDTO;
import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.dto.ListagemContaDTO;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.mapper.ContaMapper;
import br.com.acabouMony.repository.ContaRepository;
import br.com.acabouMony.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaMapper contaMapper;

    public ListagemContaDTO saveConta(CadastroContaDTO dto) {

        var usuario = usuarioRepository.findByCpf(dto.cpf());

        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuário com esse cpf não existe");
        }

        if (contaRepository.existsByNumero(dto.numero())) {
            throw new RuntimeException("Conta com esse número já existe");
        }

        Conta conta = contaMapper.toEntity(dto);

        var dataAtual = LocalDate.now();
        conta.setDataCriacao(
                new Date(dataAtual.getYear(), dataAtual.getMonthValue(), dataAtual.getDayOfMonth()));

        conta.setAtivo(true);
        conta.setUsuario(usuario.get());
        contaRepository.save(conta);
        return contaMapper.toListagemContaDTO(conta);
    }

    public List<ListagemContaDTO> getAllContas() {
        var listaContas = contaRepository.findAll();

        if (listaContas.isEmpty()) {
            throw new RuntimeException("Nenhuma conta cadastrada");
        }

        var listaListagemConta = new ArrayList<ListagemContaDTO>();

        listaContas.stream().forEach(
                c -> listaListagemConta.add(contaMapper.toListagemContaDTO(c)));

        return listaListagemConta;
    }

    public ListagemContaDTO getOneConta(UUID id) {
        var conta = contaRepository.findById(id);

        if (conta.isEmpty()) {
            throw new RuntimeException("Conta com esse ID não existe!");
        }

        return contaMapper.toListagemContaDTO(conta.get());
    }

    public ListagemContaDTO updateConta(UUID id, AtualizacaoContaDTO dto) {
        var contaEncontrada = contaRepository.findById(id);

        if (contaEncontrada.isEmpty()) {
            throw new RuntimeException("Conta com esse ID não existe!");
        }

        var conta = contaEncontrada.get();
        conta.setLimite(dto.limite());
        contaRepository.save(conta);
        return contaMapper.toListagemContaDTO(conta);
    }

    public void deleteConta(UUID id) {

        if (!contaRepository.existsById(id)) {
            throw new RuntimeException("Conta com esse ID não existe");
        }

        contaRepository.deleteById(id);
    }

    public void deleteLogicaConta(UUID id) {

        if (!contaRepository.existsById(id)) {
            throw new RuntimeException("Conta com esse ID não existe");
        }

        contaRepository.delecaoLogica(id);
    }
}
