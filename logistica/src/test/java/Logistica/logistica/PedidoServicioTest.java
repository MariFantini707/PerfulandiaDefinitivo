package Logistica.logistica;

import Logistica.logistica.Modelo.Pedido;
import Logistica.logistica.Repositorio.PedidoRepositorio;
import Logistica.logistica.Servicio.PedidoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServicioTest {
    @Mock
    private PedidoRepositorio pedidoRepositorio;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private PedidoServicio pedidoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarPedidos() {
        Pedido pedido = new Pedido();
        when(pedidoRepositorio.findAll()).thenReturn(Arrays.asList(pedido));
        List<Pedido> result = pedidoServicio.listarPedidos();
        assertEquals(1, result.size());
    }

    @Test
    void testObtenerPedidoPorId() {
        Pedido pedido = new Pedido();
        when(pedidoRepositorio.findById(1)).thenReturn(Optional.of(pedido));
        Optional<Pedido> result = pedidoServicio.obtenerPedidoPorId(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testGuardarPedido() {
        Pedido pedido = new Pedido();
        when(pedidoRepositorio.save(pedido)).thenReturn(pedido);
        Pedido result = pedidoServicio.guardarPedido(pedido);
        assertNotNull(result);
    }

    @Test
    void testEliminarPedido() {
        doNothing().when(pedidoRepositorio).deleteById(1);
        pedidoServicio.eliminarPedido(1);
        verify(pedidoRepositorio, times(1)).deleteById(1);
    }
}
