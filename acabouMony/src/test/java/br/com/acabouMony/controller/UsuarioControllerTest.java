package br.com.acabouMony.controller;

import br.com.acabouMony.dto.*;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCadastrarUsuario_Sucesso() throws Exception {
        CadastroUsuarioDTO dto = new CadastroUsuarioDTO("João", "joao@email.com", "1234", "11111111111", "999999999", new Date());

        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Cadastro de usuário feito com sucesso!"));
    }

    @Test
    void testCadastrarUsuario_Conflito() throws Exception {
        CadastroUsuarioDTO dto = new CadastroUsuarioDTO("João", "joao@email.com", "1234", "11111111111", "999999999", new Date());

        Mockito.doThrow(RuntimeException.class).when(usuarioService).saveUsuario(any());

        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Ocorreu um erro ao cadastrar usuário! Já existe um cadastro com esse email!"));
    }

    @Test
    void testDeleteUsuario_Sucesso() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/usuario/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUsuario_NotFound() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.doThrow(RuntimeException.class).when(usuarioService).deleteUsuario(id);

        mockMvc.perform(delete("/usuario/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testListarTodos_UsuariosExistem() throws Exception {
        List<Usuario> usuarios = List.of(new Usuario());
        Mockito.when(usuarioService.listarTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk());
    }

    @Test
    void testListarTodos_SemUsuarios() throws Exception {
        Mockito.when(usuarioService.listarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/usuario"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testListarUmUsuario_Sucesso() throws Exception {
        UUID id = UUID.randomUUID();
        Usuario usuario = new Usuario();
        Mockito.when(usuarioService.listarUmUsuario(id)).thenReturn(usuario);

        mockMvc.perform(get("/usuario/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void testListarUmUsuario_NotFound() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(usuarioService.listarUmUsuario(id)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/usuario/" + id))
                .andExpect(status().isNotFound());
    }
}
