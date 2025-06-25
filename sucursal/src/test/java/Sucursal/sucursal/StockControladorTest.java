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
import org.junit.jupiter.api.BeforeEach;
import Sucursal.sucursal.Modelo.Stock;

@SpringBootTest
@AutoConfigureMockMvc
class StockControladorTest {
    @Autowired
    private MockMvc mockMvc;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setIdStock(1);
        stock.setCantidad(50);
        stock.setIdProducto(1);
    }

    @Test
    void testGetStocks() throws Exception {
        mockMvc.perform(get("/api/v1/stocks"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetStockById() throws Exception {
        mockMvc.perform(get("/api/v1/stocks/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostStock() throws Exception {
        String json = "{\"cantidad\":50,\"idProducto\":1}";
        mockMvc.perform(post("/api/v1/stocks").contentType("application/json").content(json))
            .andExpect(status().isCreated());
    }
    @Test
    void testPutStock() throws Exception {
        String json = "{\"cantidad\":100,\"idProducto\":1}";
        mockMvc.perform(put("/api/v1/stocks/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteStock() throws Exception {
        mockMvc.perform(delete("/api/v1/stocks/1"))
            .andExpect(status().isNoContent()); // 204
    }
}
