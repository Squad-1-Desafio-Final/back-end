package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroUsuarioDTO;
import br.com.acabouMony.dto.ListagemUsuarioDTO;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.mapper.UsuarioMapper;
import br.com.acabouMony.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUsuario_Sucesso() {
        // Criação do DTO de entrada
        CadastroUsuarioDTO dto = new CadastroUsuarioDTO("João", "joao@email.com", "1234", "11111111111", "999999999", new Date());

        // Instância do usuário com base no DTO
        Usuario usuario = new Usuario(dto);

        // Data para o DTO de saída
        Date dtNasc = new Date(); // Supondo que a data seja relevante para o DTO

        // Criar o ListagemUsuarioDTO com os parâmetros corretos
        ListagemUsuarioDTO listagemDTO = new ListagemUsuarioDTO("João", "joao@email.com", "999999999", dtNasc);

        // Mocking do comportamento do repositório e mapper
        when(usuarioRepository.existsByEmail(dto.email())).thenReturn(false);
        when(usuarioMapper.toListagemUsuarioDTO(any(Usuario.class))).thenReturn(listagemDTO);

        // Chamada do método de serviço
        ListagemUsuarioDTO result = usuarioService.saveUsuario(dto);

        // Verificação
        assertNotNull(result);
        assertEquals("João", result.nome());
        assertEquals("joao@email.com", result.email());
        assertEquals("999999999", result.telefone());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }


    @Test
    void testSaveUsuario_EmailDuplicado() {
        CadastroUsuarioDTO dto = new CadastroUsuarioDTO("João", "joao@email.com", "1234", "11111111111", "999999999", new Date());

        when(usuarioRepository.existsByEmail(dto.email())).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.saveUsuario(dto));
        assertEquals("Este e-mail já está cadastrado.", ex.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void testDeleteUsuario_Sucesso() {
        UUID id = UUID.randomUUID();
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        usuarioService.deleteUsuario(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteUsuario_NotFound() {
        UUID id = UUID.randomUUID();
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.deleteUsuario(id));
        assertTrue(ex.getMessage().contains("Não há usuário cadastrado para o id"));
        verify(usuarioRepository, never()).deleteById(id);
    }

    @Test
    void testAtualizarNome() {
        UUID id = UUID.randomUUID();
        usuarioService.atualizarNome(id, "Novo Nome");
        verify(usuarioRepository, times(1)).atualizarNome(id, "Novo Nome");
    }

    @Test
    void testAtualizarEmail() {
        UUID id = UUID.randomUUID();
        usuarioService.atualizarEmail(id, "novo@email.com");
        verify(usuarioRepository, times(1)).atualizarEmail(id, "novo@email.com");
    }

    @Test
    void testAtualizarSenha() {
        UUID id = UUID.randomUUID();
        usuarioService.atualizarSenha(id, "novaSenha123");
        verify(usuarioRepository, times(1)).atualizarSenha(id, "novaSenha123");
    }

    @Test
    void testAtualizarTelefone() {
        UUID id = UUID.randomUUID();
        usuarioService.atualizarTelefone(id, "11999999999");
        verify(usuarioRepository, times(1)).atualizarTelefone(id, "11999999999");
    }

    @Test
    void testListarTodos() {
        List<Usuario> usuarios = List.of(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.listarTodos();

        assertEquals(2, result.size());
    }

    @Test
    void testListarUmUsuario_Sucesso() {
        UUID id = UUID.randomUUID();
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.listarUmUsuario(id);

        assertNotNull(result);
    }

    @Test
    void testListarUmUsuario_NotFound() {
        UUID id = UUID.randomUUID();
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.listarUmUsuario(id));
        assertEquals("Usuário com ID não encontrado!", ex.getMessage());
    }
}
