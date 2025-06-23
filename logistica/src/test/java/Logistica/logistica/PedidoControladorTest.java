package Logistica.logistica;

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
class PedidoControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPedidos() throws Exception {
        mockMvc.perform(get("/api/v1/pedidos"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetPedidoById() throws Exception {
        mockMvc.perform(get("/api/v1/pedidos/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostPedido() throws Exception {
        String json = "{\"estadoPedido\":\"Pendiente\",\"cantidadPedido\":3,\"idUsuario\":1,\"idProducto\":1}";
        mockMvc.perform(post("/api/v1/pedidos").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testPutPedido() throws Exception {
        String json = "{\"estadoPedido\":\"Entregado\",\"cantidadPedido\":5,\"idUsuario\":1,\"idProducto\":1}";
        mockMvc.perform(put("/api/v1/pedidos/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeletePedido() throws Exception {
        mockMvc.perform(delete("/api/v1/pedidos/1"))
            .andExpect(status().isOk());
    }
}
