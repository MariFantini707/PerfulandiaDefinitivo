package Sucursal.sucursal;

import Sucursal.sucursal.Modelo.Stock;
import Sucursal.sucursal.Repositorio.StockRepositorio;
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

class StockServicioTest {
    @Mock
    private StockRepositorio stockRepositorio;
    @InjectMocks
    private StockServicio stockServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStocks() {
        Stock stock = new Stock();
        when(stockRepositorio.findAll()).thenReturn(Arrays.asList(stock));
        List<Stock> result = stockServicio.getAllStocks();
        assertEquals(1, result.size());
    }

    @Test
    void testGetStockById() {
        Stock stock = new Stock();
        when(stockRepositorio.findById(1)).thenReturn(Optional.of(stock));
        Optional<Stock> result = stockServicio.getStockById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateStock() {
        Stock stock = new Stock();
        when(stockRepositorio.save(stock)).thenReturn(stock);
        Stock result = stockServicio.createStock(stock);
        assertNotNull(result);
    }

    @Test
    void testDeleteStock() {
        doNothing().when(stockRepositorio).deleteById(1);
        stockServicio.deleteStock(1);
        verify(stockRepositorio, times(1)).deleteById(1);
    }
}
