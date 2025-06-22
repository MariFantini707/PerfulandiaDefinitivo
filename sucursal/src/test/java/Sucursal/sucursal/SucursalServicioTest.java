package Sucursal.sucursal;

import Sucursal.sucursal.Modelo.Sucursal;
import Sucursal.sucursal.Repositorio.SucursalRepositorio;
import Sucursal.sucursal.Servicio.SucursalServicio;
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

class SucursalServicioTest {
    @Mock
    private SucursalRepositorio sucursalRepositorio;

    @InjectMocks
    private SucursalServicio sucursalServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSucursales() {
        Sucursal sucursal = new Sucursal();
        when(sucursalRepositorio.findAll()).thenReturn(Arrays.asList(sucursal));
        List<Sucursal> result = sucursalServicio.getAllSucursales();
        assertEquals(1, result.size());
    }

    @Test
    void testGetSucursalById() {
        Sucursal sucursal = new Sucursal();
        when(sucursalRepositorio.findById(1)).thenReturn(Optional.of(sucursal));
        Optional<Sucursal> result = sucursalServicio.getSucursalById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateSucursal() {
        Sucursal sucursal = new Sucursal();
        when(sucursalRepositorio.save(sucursal)).thenReturn(sucursal);
        Sucursal result = sucursalServicio.createSucursal(sucursal);
        assertNotNull(result);
    }

    @Test
    void testDeleteSucursal() {
        doNothing().when(sucursalRepositorio).deleteById(1);
        sucursalServicio.deleteSucursal(1);
        verify(sucursalRepositorio, times(1)).deleteById(1);
    }
}
