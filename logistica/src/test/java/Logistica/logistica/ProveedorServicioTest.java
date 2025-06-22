package Logistica.logistica;

import Logistica.logistica.Modelo.Proveedor;
import Logistica.logistica.Repositorio.ProveedorRepositorio;
import Logistica.logistica.Servicio.ProveedorServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProveedorServicioTest {
    @Mock
    private ProveedorRepositorio proveedorRepositorio;
    @InjectMocks
    private ProveedorServicio proveedorServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarProveedores() {
        Proveedor proveedor = new Proveedor();
        when(proveedorRepositorio.findAll()).thenReturn(Arrays.asList(proveedor));
        List<Proveedor> result = proveedorServicio.listarProveedores();
        assertEquals(1, result.size());
    }

    @Test
    void testObtenerProveedorPorId() {
        Proveedor proveedor = new Proveedor();
        when(proveedorRepositorio.findById(1)).thenReturn(Optional.of(proveedor));
        Optional<Proveedor> result = proveedorServicio.obtenerProveedorPorId(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testGuardarProveedor() {
        Proveedor proveedor = new Proveedor();
        when(proveedorRepositorio.save(proveedor)).thenReturn(proveedor);
        Proveedor result = proveedorServicio.guardarProveedor(proveedor);
        assertNotNull(result);
    }

    @Test
    void testEliminarProveedor() {
        doNothing().when(proveedorRepositorio).deleteById(1);
        proveedorServicio.eliminarProveedor(1);
        verify(proveedorRepositorio, times(1)).deleteById(1);
    }
}
