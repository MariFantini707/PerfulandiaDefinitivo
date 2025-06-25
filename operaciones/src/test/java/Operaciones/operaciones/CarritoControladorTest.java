package Operaciones.operaciones;

import Operaciones.operaciones.modelo.Carrito;
import Operaciones.operaciones.servicio.CarritoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarritoControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarritoServicio carritoServicio;

    @Autowired
    private ObjectMapper objectMapper;

    private Carrito carrito;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        carrito.setIdCarrito(1);
        carrito.setCantidadCarrito(3);
    }

    @Test
    void testGetCarritos() throws Exception {
        when(carritoServicio.getAllCarritos()).thenReturn(Collections.singletonList(carrito));
        mockMvc.perform(get("/api/v1/carritos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idCarrito").value(carrito.getIdCarrito()))
            .andExpect(jsonPath("$[0].cantidadCarrito").value(carrito.getCantidadCarrito()));
    }

    @Test
    void testGetCarritoById() throws Exception {
        when(carritoServicio.getCarritosById(1)).thenReturn(java.util.Optional.of(carrito));
        mockMvc.perform(get("/api/v1/carritos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCarrito").value(carrito.getIdCarrito()))
            .andExpect(jsonPath("$.cantidadCarrito").value(carrito.getCantidadCarrito()));
    }

    @Test
    void testPostCarrito() throws Exception {
        when(carritoServicio.createCarrito(any(Carrito.class))).thenReturn(carrito);
        mockMvc.perform(post("/api/v1/carritos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carrito)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idCarrito").value(carrito.getIdCarrito()))
            .andExpect(jsonPath("$.cantidadCarrito").value(carrito.getCantidadCarrito()));
    }

    @Test
    void testPutCarrito() throws Exception {
        when(carritoServicio.updateCarritos(eq(1), any(Carrito.class))).thenReturn(carrito);
        mockMvc.perform(put("/api/v1/carritos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carrito)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCarrito").value(carrito.getIdCarrito()))
            .andExpect(jsonPath("$.cantidadCarrito").value(carrito.getCantidadCarrito()));
    }

    @Test
    void testDeleteCarrito() throws Exception {
        doNothing().when(carritoServicio).deleteCarritos(1);
        mockMvc.perform(delete("/api/v1/carritos/1"))
            .andExpect(status().isOk());
    }
}
