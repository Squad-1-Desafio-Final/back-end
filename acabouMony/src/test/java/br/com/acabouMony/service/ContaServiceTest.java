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

import java.util.Optional;

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
    private Conta conta;

    @Test
    @DisplayName("Conta com esse número já existe - erro 409")
    void erro409() {
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

    }
}
