package Sucursal.sucursal;

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
class SucursalControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetSucursales() throws Exception {
        mockMvc.perform(get("/api/v1/sucursales"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetSucursalById() throws Exception {
        mockMvc.perform(get("/api/v1/sucursales/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostSucursal() throws Exception {
        String json = "{\"nombreSucursal\":\"Test\",\"direccionSucursal\":\"Calle 123\",\"telefonoSucursal\":1234567}";
        mockMvc.perform(post("/api/v1/sucursales").contentType("application/json").content(json))
            .andExpect(status().isCreated());
    }
    @Test
    void testPutSucursal() throws Exception {
        String json = "{\"nombreSucursal\":\"Test2\",\"direccionSucursal\":\"Calle 456\",\"telefonoSucursal\":7654321}";
        mockMvc.perform(put("/api/v1/sucursales/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteSucursal() throws Exception {
        mockMvc.perform(delete("/api/v1/sucursales/1"))
            .andExpect(status().isNoContent()); // 204
    }
}
