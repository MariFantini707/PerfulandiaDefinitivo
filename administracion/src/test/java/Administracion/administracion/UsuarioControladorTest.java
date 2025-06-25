package Administracion.administracion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import com.fasterxml.jackson.databind.ObjectMapper;
import Administracion.administracion.Modelo.Usuario;
import Administracion.administracion.Servicio.UsuarioServicio;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Collections;

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


    @MockitoBean
    private UsuarioServicio usuarioServicio;
    private Usuario usuario;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombreUsuario("Test");
        usuario.setRutUsuario("12345678-9");
        usuario.setCorreoUsuario("test@test.com");
        usuario.setRolUsuario("admin");
    }
    @Test
    void testPostUsuario() throws Exception {
        when(usuarioServicio.save(any(Usuario.class))).thenReturn(usuario);
        String json = objectMapper.writeValueAsString(usuario);
        mockMvc.perform(post("/api/v1/usuario").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idUsuario").value(usuario.getIdUsuario()))
            .andExpect(jsonPath("$.nombreUsuario").value(usuario.getNombreUsuario()))
            .andExpect(jsonPath("$.rutUsuario").value(usuario.getRutUsuario()))
            .andExpect(jsonPath("$.correoUsuario").value(usuario.getCorreoUsuario()))
            .andExpect(jsonPath("$.rolUsuario").value(usuario.getRolUsuario()));
    }
    @Test
    void testGetUsuarios() throws Exception {
        when(usuarioServicio.findAll()).thenReturn(Collections.singletonList(usuario));
        mockMvc.perform(get("/api/v1/usuario"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idUsuario").value(usuario.getIdUsuario()))
            .andExpect(jsonPath("$[0].nombreUsuario").value(usuario.getNombreUsuario()))
            .andExpect(jsonPath("$[0].rutUsuario").value(usuario.getRutUsuario()))
            .andExpect(jsonPath("$[0].correoUsuario").value(usuario.getCorreoUsuario()))
            .andExpect(jsonPath("$[0].rolUsuario").value(usuario.getRolUsuario()));
    }
    @Test
    void testGetUsuarioById() throws Exception {
        when(usuarioServicio.findById(1)).thenReturn(java.util.Optional.of(usuario));
        mockMvc.perform(get("/api/v1/usuario/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idUsuario").value(usuario.getIdUsuario()))
            .andExpect(jsonPath("$.nombreUsuario").value(usuario.getNombreUsuario()))
            .andExpect(jsonPath("$.rutUsuario").value(usuario.getRutUsuario()))
            .andExpect(jsonPath("$.correoUsuario").value(usuario.getCorreoUsuario()))
            .andExpect(jsonPath("$.rolUsuario").value(usuario.getRolUsuario()));
    }
    @Test
    void testPutUsuario() throws Exception {
        when(usuarioServicio.actualizar(eq(1), any(Usuario.class))).thenReturn(java.util.Optional.of(usuario));
        String json = objectMapper.writeValueAsString(usuario);
        mockMvc.perform(put("/api/v1/usuario/1").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idUsuario").value(usuario.getIdUsuario()))
            .andExpect(jsonPath("$.nombreUsuario").value(usuario.getNombreUsuario()))
            .andExpect(jsonPath("$.rutUsuario").value(usuario.getRutUsuario()))
            .andExpect(jsonPath("$.correoUsuario").value(usuario.getCorreoUsuario()))
            .andExpect(jsonPath("$.rolUsuario").value(usuario.getRolUsuario()));
    }
    @Test
    void testDeleteUsuario() throws Exception {
        when(usuarioServicio.delete(1)).thenReturn(true);
        mockMvc.perform(delete("/api/v1/usuario/1"))
            .andExpect(status().isNoContent());
    }
}
