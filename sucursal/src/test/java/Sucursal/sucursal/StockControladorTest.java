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
import Sucursal.sucursal.Servicio.StockServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class StockControladorTest {
    @Autowired
    private MockMvc mockMvc;

    private Stock stock;

    @MockitoBean
    private StockServicio stockServicio;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setIdStock(1);
        stock.setCantidad(50);
        stock.setIdProducto(1);
    }

    @Test
    void testGetStocks() throws Exception {
        when(stockServicio.getAllStocks()).thenReturn(Collections.singletonList(stock));
        mockMvc.perform(get("/api/v1/stocks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idStock").value(stock.getIdStock()))
            .andExpect(jsonPath("$[0].cantidad").value(stock.getCantidad()))
            .andExpect(jsonPath("$[0].idProducto").value(stock.getIdProducto()));
    }
    @Test
    void testGetStockById() throws Exception {
        when(stockServicio.getStockById(1)).thenReturn(java.util.Optional.of(stock));
        mockMvc.perform(get("/api/v1/stocks/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idStock").value(stock.getIdStock()))
            .andExpect(jsonPath("$.cantidad").value(stock.getCantidad()))
            .andExpect(jsonPath("$.idProducto").value(stock.getIdProducto()));
    }
    @Test
    void testPostStock() throws Exception {
        when(stockServicio.createStock(any(Stock.class))).thenReturn(stock);
        String json = objectMapper.writeValueAsString(stock);
        mockMvc.perform(post("/api/v1/stocks").contentType("application/json").content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idStock").value(stock.getIdStock()))
            .andExpect(jsonPath("$.cantidad").value(stock.getCantidad()))
            .andExpect(jsonPath("$.idProducto").value(stock.getIdProducto()));
    }
    @Test
    void testPutStock() throws Exception {
        when(stockServicio.updateStock(eq(1), any(Stock.class))).thenReturn(stock);
        String json = objectMapper.writeValueAsString(stock);
        mockMvc.perform(put("/api/v1/stocks/1").contentType("application/json").content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idStock").value(stock.getIdStock()))
            .andExpect(jsonPath("$.cantidad").value(stock.getCantidad()))
            .andExpect(jsonPath("$.idProducto").value(stock.getIdProducto()));
    }
    @Test
    void testDeleteStock() throws Exception {
        doNothing().when(stockServicio).deleteStock(1);
        mockMvc.perform(delete("/api/v1/stocks/1"))
            .andExpect(status().isOk());
    }
}
