package Operaciones.operaciones;

import Operaciones.operaciones.modelo.Venta;
import Operaciones.operaciones.repositorio.VentaRepositorio;
import Operaciones.operaciones.servicio.VentaServicio;
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

class VentaServicioTest {
    @Mock
    private VentaRepositorio ventaRepositorio;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private VentaServicio ventaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVentas() {
        Venta venta = new Venta();
        when(ventaRepositorio.findAll()).thenReturn(Arrays.asList(venta));
        List<Venta> result = ventaServicio.getAllVentas();
        assertEquals(1, result.size());
    }

    @Test
    void testGetVentaById() {
        Venta venta = new Venta();
        when(ventaRepositorio.findById(1)).thenReturn(Optional.of(venta));
        Optional<Venta> result = ventaServicio.getVentaById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateVenta() {
        Venta venta = new Venta();
        when(ventaRepositorio.save(venta)).thenReturn(venta);
        Venta result = ventaServicio.createVenta(venta);
        assertNotNull(result);
    }

    @Test
    void testDeleteVenta() {
        doNothing().when(ventaRepositorio).deleteById(1);
        ventaServicio.deleteVenta(1);
        verify(ventaRepositorio, times(1)).deleteById(1);
    }
}
