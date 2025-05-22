    package br.com.acabouMony.controller;

    import br.com.acabouMony.dto.CadastroEnderecoDTO;
    import br.com.acabouMony.entity.Endereco;
    import br.com.acabouMony.service.EnderecoService;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
    import org.springframework.http.MediaType;
    import org.springframework.test.context.bean.override.mockito.MockitoBean;
    import org.springframework.test.web.servlet.MockMvc;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.UUID;

    import static org.mockito.Mockito.*;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    @WebMvcTest(EnderecoController.class)
    class EnderecoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private EnderecoService enderecoService;

        private ObjectMapper objectMapper;
        private UUID id;
        private Endereco endereco;

        @BeforeEach
        void setUp() {
            objectMapper = new ObjectMapper();
            id = UUID.randomUUID();
            endereco = new Endereco();
            endereco.setId(id);
            endereco.setLogradouro("Rua A");
        }

        @Test
        void deveCadastrarEnderecoComSucesso() throws Exception {
            CadastroEnderecoDTO dto = new CadastroEnderecoDTO(
                    "Rua Teste",
                    123,
                    "Apto 101",
                    "Bairro Legal",
                    "Cidade Teste",
                    "SP",
                    "12345-678"
            );

            String json = objectMapper.writeValueAsString(dto);

            mockMvc.perform(post("/endereco")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(content().string("Cadastro de endereço feito com sucesso!"));

            verify(enderecoService).saveEndereco(any());
        }

        @Test
        void deveDeletarEnderecoComSucesso() throws Exception {
            doNothing().when(enderecoService).deleteEndereco(id);

            mockMvc.perform(delete("/endereco/" + id))
                    .andExpect(status().isNoContent());

            verify(enderecoService).deleteEndereco(id);
        }

        @Test
        void deveRetornarNotFoundAoDeletarEnderecoInexistente() throws Exception {
            doThrow(new RuntimeException()).when(enderecoService).deleteEndereco(id);

            mockMvc.perform(delete("/endereco/" + id))
                    .andExpect(status().isNotFound());
        }

        @Test
        void deveListarTodosComConteudo() throws Exception {
            when(enderecoService.listarTodos()).thenReturn(List.of(endereco));

            mockMvc.perform(get("/endereco"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(id.toString()));
        }

        @Test
        void deveListarTodosSemConteudo() throws Exception {
            when(enderecoService.listarTodos()).thenReturn(List.of());

            mockMvc.perform(get("/endereco"))
                    .andExpect(status().isNoContent());
        }

        @Test
        void deveListarUmEnderecoComSucesso() throws Exception {
            when(enderecoService.listarUmEndereco(id)).thenReturn(endereco);

            mockMvc.perform(get("/endereco/" + id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id.toString()));
        }

        @Test
        void deveRetornarNotFoundAoBuscarEnderecoInexistente() throws Exception {
            when(enderecoService.listarUmEndereco(id)).thenThrow(new RuntimeException());

            mockMvc.perform(get("/endereco/" + id))
                    .andExpect(status().isNotFound());
        }

        @Test
        void deveAtualizarLogradouroComSucesso() throws Exception {
            Map<String, String> body = Map.of("logradouro", "Nova Rua");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(enderecoService).atualizarLogradouro(id, "Nova Rua");
        }

        @Test
        void deveRetornarBadRequestAoAtualizarLogradouroInvalido() throws Exception {
            Map<String, String> body = Map.of("logradouro", "");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void deveAtualizarNumeroComSucesso() throws Exception {
            Map<String, Integer> body = Map.of("numero", 123);
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/numero")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(enderecoService).atualizarNumero(id, 123);
        }

        @Test
        void deveRetornarBadRequestAoAtualizarNumeroNulo() throws Exception {
            Map<String, Integer> body = new HashMap<>();
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/numero")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void deveAtualizarComplementoComSucesso() throws Exception {
            Map<String, String> body = Map.of("complemento", "Apto 202");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/complemento")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(enderecoService).atualizarComplemento(id, "Apto 202");
        }

        @Test
        void deveRetornarBadRequestAoAtualizarComplementoInvalido() throws Exception {
            Map<String, String> body = Map.of("complemento", "");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/complemento")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void deveAtualizarBairroComSucesso() throws Exception {
            Map<String, String> body = Map.of("bairro", "Centro");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/bairro")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(enderecoService).atualizarBairro(id, "Centro");
        }

        @Test
        void deveRetornarBadRequestAoAtualizarBairroInvalido() throws Exception {
            Map<String, String> body = Map.of("bairro", "");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/bairro")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void deveAtualizarCidadeComSucesso() throws Exception {
            Map<String, String> body = Map.of("cidade", "São Paulo");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/cidade")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(enderecoService).atualizarCidade(id, "São Paulo");
        }

        @Test
        void deveRetornarBadRequestAoAtualizarCidadeInvalida() throws Exception {
            Map<String, String> body = Map.of("cidade", "");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/cidade")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void deveAtualizarEstadoComSucesso() throws Exception {
            Map<String, String> body = Map.of("estado", "SP");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/estado")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(enderecoService).atualizarEstado(id, "SP");
        }

        @Test
        void deveRetornarBadRequestAoAtualizarEstadoInvalido() throws Exception {
            Map<String, String> body = Map.of("estado", "");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/estado")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void deveAtualizarCEPComSucesso() throws Exception {
            Map<String, String> body = Map.of("cep", "12345-678");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/cep")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(enderecoService).atualizarCEP(id, "12345-678");
        }

        @Test
        void deveRetornarBadRequestAoAtualizarCEPInvalido() throws Exception {
            Map<String, String> body = Map.of("cep", "");
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/cep")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        private void testPatchComString(String campo, String valor) throws Exception {
            Map<String, String> body = Map.of(campo, valor);
            String json = objectMapper.writeValueAsString(body);

            mockMvc.perform(patch("/endereco/" + id + "/" + campo)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());
        }
    }
