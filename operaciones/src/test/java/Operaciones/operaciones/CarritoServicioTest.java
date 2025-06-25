package Operaciones.operaciones;

import Operaciones.operaciones.modelo.Carrito;
import Operaciones.operaciones.repositorio.CarritoRepositorio;
import Operaciones.operaciones.servicio.CarritoServicio;
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

class CarritoServicioTest {
    @Mock
    private CarritoRepositorio carritoRepositorio;
    @InjectMocks
    private CarritoServicio carritoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCarritos() {
        Carrito carrito = new Carrito();
        when(carritoRepositorio.findAll()).thenReturn(Arrays.asList(carrito));
        List<Carrito> result = carritoServicio.getAllCarritos();
        assertEquals(1, result.size());
    }

    @Test
    void testGetCarritosById() {
        Carrito carrito = new Carrito();
        when(carritoRepositorio.findById(1)).thenReturn(Optional.of(carrito));
        Optional<Carrito> result = carritoServicio.getCarritosById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateCarrito() {
        Carrito carrito = new Carrito();
        when(carritoRepositorio.save(carrito)).thenReturn(carrito);
        Carrito result = carritoServicio.createCarrito(carrito);
        assertNotNull(result);
    }

    @Test
    void testUpdateCarritos() {
        Carrito carrito = new Carrito();
        carrito.setCantidadCarrito(5);
        when(carritoRepositorio.findById(1)).thenReturn(Optional.of(carrito));
        when(carritoRepositorio.save(any(Carrito.class))).thenReturn(carrito);
        Carrito result = carritoServicio.updateCarritos(1, carrito);
        assertNotNull(result);
        assertEquals(5, result.getCantidadCarrito());
    }

    @Test
    void testDeleteCarritos() {
        doNothing().when(carritoRepositorio).deleteById(1);
        carritoServicio.deleteCarritos(1);
        verify(carritoRepositorio, times(1)).deleteById(1);
    }
}
