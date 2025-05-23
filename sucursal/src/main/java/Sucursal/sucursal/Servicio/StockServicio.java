package Sucursal.sucursal.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import Sucursal.sucursal.Modelo.Stock;
import Sucursal.sucursal.Repositorio.StockRepositorio;

@Service
public class StockServicio {
    @Autowired
    private StockRepositorio stockRepositorio;

    public List<Stock> getAllStocks() {
        return stockRepositorio.findAll();
    }

    public Optional<Stock> getStockById(int id) {
        return stockRepositorio.findById(id);
    }

    public Stock createStock(Stock stock) {
        return stockRepositorio.save(stock);
    }

    public Stock updateStock(Integer id, Stock stock) {
        Optional<Stock> stockTemp = stockRepositorio.findById(id);
        if (stockTemp.isPresent()) {
            Stock stockActualizar = stockTemp.get();
            // Actualiza los campos necesarios aquí
            return stockRepositorio.save(stockActualizar);
        }
        throw new RuntimeException("No se encontró el stock");
    }

    public void deleteStock(int id) {
        stockRepositorio.deleteById(id);
    }
}
