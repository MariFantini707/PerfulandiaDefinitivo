package Operaciones.operaciones;

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
import Operaciones.operaciones.modelo.Venta;
import Operaciones.operaciones.modelo.Carrito;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import Operaciones.operaciones.servicio.VentaServicio;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
@AutoConfigureMockMvc
class VentaControladorTest {
    @Autowired
    private MockMvc mockMvc;
    
    private Venta venta;
    private Carrito carrito;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VentaServicio ventaServicio;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        carrito.setIdCarrito(1);
        carrito.setCantidadCarrito(3);

        venta = new Venta();
        venta.setIdVenta(1);
        venta.setFechaVenta(java.sql.Date.valueOf("2024-01-01"));
        venta.setTotalVenta(1000);
        venta.setCarrito(carrito);
        venta.setIdUsuario(1);
    }

    @Test
    void testGetVentas() throws Exception {
        mockMvc.perform(get("/api/v1/ventas"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetVentaById() throws Exception {
        mockMvc.perform(get("/api/v1/ventas/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostVenta() throws Exception {
        when(ventaServicio.createVenta(any(Venta.class))).thenReturn(venta);
        mockMvc.perform(post("/api/v1/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(venta)))
            .andExpect(status().isOk());
    }
    @Test
    void testPutVenta() throws Exception {
        when(ventaServicio.getVentaById(1)).thenReturn(java.util.Optional.of(venta));
        when(ventaServicio.updateVenta(eq(1), any(Venta.class))).thenReturn(venta);
        mockMvc.perform(put("/api/v1/ventas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(venta)))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteVenta() throws Exception {
        mockMvc.perform(delete("/api/v1/ventas/1"))
            .andExpect(status().isNoContent()); // 204
    }
}
