package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroEnderecoDTO;
import br.com.acabouMony.dto.ListagemEnderecoDTO;
import br.com.acabouMony.entity.Endereco;
import br.com.acabouMony.mapper.EnderecoMapper;
import br.com.acabouMony.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private EnderecoMapper enderecoMapper;

    @InjectMocks
    private EnderecoService enderecoService;

    private Endereco endereco;
    private CadastroEnderecoDTO cadastroEnderecoDTO;

    @BeforeEach
    void setUp() {
        endereco = new Endereco(UUID.randomUUID(), "Rua A", 123, "Apto 101", "Bairro X", "Cidade Y", "SP", "12345-678");
        cadastroEnderecoDTO = new CadastroEnderecoDTO("Rua A", 123, "Apto 101", "Bairro X", "Cidade Y", "SP", "12345-678");
    }

    @Test
    void testSaveEndereco() {
        ListagemEnderecoDTO listagemEnderecoDTO = new ListagemEnderecoDTO(
                "Rua A", 123, "Apto 101", "Bairro X", "Cidade Y", "SP", "12345-678"
        );
        when(enderecoMapper.toListagemEnderecoDTO(any(Endereco.class))).thenReturn(listagemEnderecoDTO);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        ListagemEnderecoDTO result = enderecoService.saveEndereco(cadastroEnderecoDTO);

        assertNotNull(result);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    void testDeleteEndereco_Success() {
        when(enderecoRepository.findById(any(UUID.class))).thenReturn(Optional.of(endereco));

        enderecoService.deleteEndereco(endereco.getId());

        verify(enderecoRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void testDeleteEndereco_NotFound() {
        UUID id = UUID.randomUUID();
        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> enderecoService.deleteEndereco(id));
        assertEquals("Não há endereço cadastrada para o id " + id, thrown.getMessage());
    }


    @Test
    void testListarTodos() {
        when(enderecoRepository.findAll()).thenReturn(List.of(endereco));

        List<Endereco> result = enderecoService.listarTodos();

        assertFalse(result.isEmpty());
        verify(enderecoRepository, times(1)).findAll();
    }

    @Test
    void testListarUmEndereco_Success() {
        when(enderecoRepository.findById(any(UUID.class))).thenReturn(Optional.of(endereco));

        Endereco result = enderecoService.listarUmEndereco(endereco.getId());

        assertNotNull(result);
        verify(enderecoRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testListarUmEndereco_NotFound() {
        when(enderecoRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> enderecoService.listarUmEndereco(UUID.randomUUID()));
        assertEquals("Usuário com ID não encontrado!", thrown.getMessage());
    }
}