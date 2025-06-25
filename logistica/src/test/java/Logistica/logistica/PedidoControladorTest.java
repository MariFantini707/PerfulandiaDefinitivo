package Logistica.logistica;

import Logistica.logistica.Controlador.PedidoControlador;
import Logistica.logistica.Modelo.Pedido;
import Logistica.logistica.Modelo.ProductoDto;
import Logistica.logistica.Servicio.PedidoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoControlador.class)
public class PedidoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoServicio pedidoServicio;

    @Autowired
    private ObjectMapper objectMapper;

    private Pedido pedido;
    private ProductoDto productoDto;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setEstadoPedido("Pendiente");
        pedido.setCantidadPedido(3);
        pedido.setIdUsuario(4);
        pedido.setIdProducto(7);

        productoDto = new ProductoDto();
        productoDto.setIdProducto(7);
        productoDto.setNombreProducto("Monitor LED");
        productoDto.setPrecioProducto(85000);
        productoDto.setCategoriaProducto("Pantallas");
    }

    @Test
    public void testListarPedidos() throws Exception {
        when(pedidoServicio.listarPedidos()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/v1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPedido").value(1))
                .andExpect(jsonPath("$[0].estadoPedido").value("Pendiente"));
    }

    @Test
    public void testObtenerPedidoPorId() throws Exception {
        when(pedidoServicio.obtenerPedidoPorId(1)).thenReturn(Optional.of(pedido));

        mockMvc.perform(get("/api/v1/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPedido").value(1))
                .andExpect(jsonPath("$.estadoPedido").value("Pendiente"));
    }

    @Test
    public void testCrearPedido() throws Exception {
        when(pedidoServicio.guardarPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPedido").value(1))
                .andExpect(jsonPath("$.estadoPedido").value("Pendiente"));
    }

    @Test
    public void testActualizarPedido() throws Exception {
        when(pedidoServicio.obtenerPedidoPorId(1)).thenReturn(Optional.of(pedido));
        when(pedidoServicio.actualizarPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(put("/api/v1/pedidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPedido").value(1))
                .andExpect(jsonPath("$.estadoPedido").value("Pendiente"));
    }


    @Test
    public void testEliminarPedido() throws Exception {
        when(pedidoServicio.obtenerPedidoPorId(1)).thenReturn(Optional.of(pedido));
        doNothing().when(pedidoServicio).eliminarPedido(1);

        mockMvc.perform(delete("/api/v1/pedidos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testObtenerProductoDePedido() throws Exception {
        when(pedidoServicio.obtenerProductoDePedido(1)).thenReturn(productoDto);

        mockMvc.perform(get("/api/v1/pedidos/1/producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".idProducto").value(7))
                .andExpect(jsonPath(".nombreProducto").value("Monitor LED"));
    }


    @TestConfiguration
    static class PedidoServicioTestConfig {
        @Bean
        public PedidoServicio pedidoServicio() {
            return Mockito.mock(PedidoServicio.class);
        }
    }
}

