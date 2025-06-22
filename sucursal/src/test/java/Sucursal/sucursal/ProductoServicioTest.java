package Sucursal.sucursal;

import Sucursal.sucursal.Modelo.Producto;
import Sucursal.sucursal.Repositorio.ProductoRepositorio;
import Sucursal.sucursal.Servicio.ProductoServicio;
import Sucursal.sucursal.Servicio.StockServicio;
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

class ProductoServicioTest {
    @Mock
    private ProductoRepositorio productoRepositorio;
    @Mock
    private StockServicio stockServicio;
    @InjectMocks
    private ProductoServicio productoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProductos() {
        Producto producto = new Producto();
        when(productoRepositorio.findAll()).thenReturn(Arrays.asList(producto));
        List<Producto> result = productoServicio.getAllProductos();
        assertEquals(1, result.size());
    }

    @Test
    void testGetProductoById() {
        Producto producto = new Producto();
        when(productoRepositorio.findById(1)).thenReturn(Optional.of(producto));
        Optional<Producto> result = productoServicio.getProductoById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateProducto() {
        Producto producto = new Producto();
        when(productoRepositorio.save(producto)).thenReturn(producto);
        Producto result = productoServicio.createProducto(producto);
        assertNotNull(result);
    }

    @Test
    void testDeleteProducto() {
        doNothing().when(productoRepositorio).deleteById(1);
        productoServicio.deleteProducto(1);
        verify(productoRepositorio, times(1)).deleteById(1);
    }
}
