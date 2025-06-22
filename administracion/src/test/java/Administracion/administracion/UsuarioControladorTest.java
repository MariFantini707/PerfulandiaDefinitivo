package Administracion.administracion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUsuarios() throws Exception {
        mockMvc.perform(get("/api/v1/usuario"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetUsuarioById() throws Exception {
        mockMvc.perform(get("/api/v1/usuario/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostUsuario() throws Exception {
        String json = "{\"nombreUsuario\":\"Test\",\"rutUsuario\":\"1-9\",\"correoUsuario\":\"test@test.com\",\"rolUsuario\":\"admin\"}";
        mockMvc.perform(post("/api/v1/usuario").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testPutUsuario() throws Exception {
        String json = "{\"nombreUsuario\":\"Test2\",\"rutUsuario\":\"2-7\",\"correoUsuario\":\"test2@test.com\",\"rolUsuario\":\"user\"}";
        mockMvc.perform(put("/api/v1/usuario/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteUsuario() throws Exception {
        mockMvc.perform(delete("/api/v1/usuario/1"))
            .andExpect(status().isOk());
    }
}
