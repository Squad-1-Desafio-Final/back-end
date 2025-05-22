package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.dto.ListagemContaDTO;
import br.com.acabouMony.entity.Conta;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.mapper.ContaMapper;
import br.com.acabouMony.repository.ContaRepository;
import br.com.acabouMony.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ContaMapper contaMapper;

    @Mock
    private ListagemContaDTO listagemContaDTO;

    @Mock
    private CadastroContaDTO cadastroContaDTO;

    @Mock
    private Optional<Usuario> usuario;

    @Mock
    private Optional<Conta> contaOptional;

    @Mock
    private Conta conta;

    @Mock
    private List<Conta> listaContas;

    @Mock
    private List<ListagemContaDTO> listagemContaDTOS;

    @Mock
    private UUID uuid;

    @Test
    @DisplayName("Conta com esse número já existe - codigo 409")
    void codigo409() {
        // ARRANGE
        when(usuarioRepository.findByCpf(cadastroContaDTO.cpf()))
                .thenReturn(usuario);

        when(contaRepository.existsByNumero(cadastroContaDTO.numero()))
                .thenThrow(new RuntimeException("Número de conta já existe"));

        // ACT & ASSERT
        Assertions.assertThrows(RuntimeException.class, () -> {
            contaService.saveConta(cadastroContaDTO);
        });
    }

    @Test
    @DisplayName(" Criação de Conta - Código 201")
    void criacaoConta() {
        // ARRANGE
        when(usuarioRepository.findByCpf(cadastroContaDTO.cpf()))
                .thenReturn(usuario);

        when(contaMapper.toEntity(cadastroContaDTO)).thenReturn(conta);

        // ACT
        contaService.saveConta(cadastroContaDTO);

        // ASSERT

        then(contaRepository).should().save(conta);
    }

    @Test
    @DisplayName("Listagem de conta vazio - codigo 204")
    void codigo204() {

        // ARRANGE
        when(contaRepository.findAll()).thenReturn(Collections.emptyList());

        // ACT & ASSERT
        Assertions.assertThrows(RuntimeException.class, () -> {
            contaService.getAllContas();
        });
    }

    @Test
    @DisplayName("Listagem de contas - código 200")
    void codigo200_listagem_contas() {
        // ARRANGE
        List<Conta> listaContas = List.of(conta);
        List<ListagemContaDTO> listagemContaDTOS = List.of(listagemContaDTO);

        when(contaRepository.findAll()).thenReturn(listaContas);
        when(contaMapper.toListagemContaDTO(conta)).thenReturn(listagemContaDTO);

        // ACT
        List<ListagemContaDTO> response = contaService.getAllContas();

        // ASSERT
        Assertions.assertEquals(listagemContaDTOS, response);
    }

    @Test
    @DisplayName("Listagem de conta - código 200")
    void codigo200_listagem_conta_por_id() {
        // ARRANGE
        when(contaRepository.findById(uuid)).thenReturn(contaOptional);
        when(contaOptional.get()).thenReturn(conta); // precisa disso porque você está mockando Optional
        when(contaMapper.toListagemContaDTO(conta)).thenReturn(listagemContaDTO);

        // ACT
        var response = contaService.getOneConta(uuid);

        // ASSERT
        Assertions.assertEquals(listagemContaDTO, response);
    }

    @Test
    @DisplayName("Deleção de usuário")
    void delecaoUsuario() {

        // ARRANGE
        when(contaRepository.existsById(uuid)).thenReturn(true);
        // ACT
        contaService.deleteConta(uuid);

        // ASSERTION
        then(contaRepository).should().deleteById(uuid);
    }

    @Test
    @DisplayName("Deleção de usuário -  Código 404")
    void delecaoUsuario_nao_encontrado() {

        // ARRANGE
        when(contaRepository.existsById(uuid)).thenReturn(false);

        //ACT + ASSERTION
        Assertions.assertThrows(RuntimeException.class, () -> {
            contaService.deleteConta(uuid);
        });
    }

    @Test
    @DisplayName("Deleção de usuário lógica")
    void delecaoUsuarioLogica() {

        // ARRANGE
        when(contaRepository.existsById(uuid)).thenReturn(true);
        // ACT
        contaService.deleteLogicaConta(uuid);
        // ASSERTION
        then(contaRepository).should().delecaoLogica(uuid);
    }

    @Test
    @DisplayName("Deleção de usuário Lógica -  Código 404")
    void delecaoUsuarioLogica_nao_encontrado() {

        // ARRANGE
        when(contaRepository.existsById(uuid)).thenReturn(false);

        //ACT + ASSERTION
        Assertions.assertThrows(RuntimeException.class, () -> {
            contaService.deleteLogicaConta(uuid);
        });
    }
}
