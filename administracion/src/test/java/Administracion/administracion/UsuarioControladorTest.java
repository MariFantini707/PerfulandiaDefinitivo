package Administracion.administracion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import Administracion.administracion.Repositorio.UsuarioRepositorio;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsuarioControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    private String usuarioId;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        usuarioRepositorio.deleteAll(); // Limpia la tabla antes de cada test
        String json = "{\"nombreUsuario\":\"Test\",\"rutUsuario\":\"1-9\",\"correoUsuario\":\"test@test.com\",\"rolUsuario\":\"admin\"}";
        String response = mockMvc.perform(post("/api/v1/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Extrae el ID del usuario creado desde el JSON de respuesta
        JsonNode node = objectMapper.readTree(response);
        usuarioId = node.has("idUsuario") ? node.get("idUsuario").asText() : "1";
    }
    @Test
    void testPostUsuario() throws Exception {
        String json = "{\"nombreUsuario\":\"Test2\",\"rutUsuario\":\"2-7\",\"correoUsuario\":\"test2@test.com\",\"rolUsuario\":\"user\"}";
        mockMvc.perform(post("/api/v1/usuario").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testGetUsuarios() throws Exception {
        mockMvc.perform(get("/api/v1/usuario"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetUsuarioById() throws Exception {
        mockMvc.perform(get("/api/v1/usuario/" + usuarioId))
            .andExpect(status().isOk());
    }
    @Test
    void testPutUsuario() throws Exception {
        // Crea un usuario para este test
        String jsonCreate = "{\"nombreUsuario\":\"TestPut\",\"rutUsuario\":\"3-3\",\"correoUsuario\":\"put@test.com\",\"rolUsuario\":\"user\"}";
        String response = mockMvc.perform(post("/api/v1/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCreate))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JsonNode node = objectMapper.readTree(response);
        String id = node.has("idUsuario") ? node.get("idUsuario").asText() : "1";
        String json = "{\"nombreUsuario\":\"TestEdit\",\"rutUsuario\":\"3-3\",\"correoUsuario\":\"edit@test.com\",\"rolUsuario\":\"admin\"}";
        mockMvc.perform(put("/api/v1/usuario/" + id).contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteUsuario() throws Exception {
        mockMvc.perform(delete("/api/v1/usuario/" + usuarioId))
            .andExpect(status().isNoContent()); // 204
    }
}
