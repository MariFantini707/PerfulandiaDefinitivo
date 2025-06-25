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
class ProductoControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetProductos() throws Exception {
        mockMvc.perform(get("/api/v1/productos"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetProductoById() throws Exception {
        mockMvc.perform(get("/api/v1/productos/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostProducto() throws Exception {
        String json = "{\"nombreProducto\":\"Test\",\"precioProducto\":1000,\"categoriaProducto\":\"Categoria\",\"idCarrito\":1}";
        mockMvc.perform(post("/api/v1/productos").contentType("application/json").content(json))
            .andExpect(status().isCreated());
    }
    @Test
    void testPutProducto() throws Exception {
        String json = "{\"nombreProducto\":\"Test2\",\"precioProducto\":2000,\"categoriaProducto\":\"Categoria2\",\"idCarrito\":1}";
        mockMvc.perform(put("/api/v1/productos/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteProducto() throws Exception {
        mockMvc.perform(delete("/api/v1/productos/1"))
            .andExpect(status().isNoContent()); // 204
    }
    @Test
    void testAumentarStock() throws Exception {
        mockMvc.perform(put("/api/v1/productos/aumentarStock/1/10"))
            .andExpect(status().isOk());
    }
}
