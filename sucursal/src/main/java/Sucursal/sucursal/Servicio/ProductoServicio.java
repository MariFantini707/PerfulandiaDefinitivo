package Sucursal.sucursal.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import Sucursal.sucursal.Modelo.Producto;
import Sucursal.sucursal.Modelo.Stock;
import Sucursal.sucursal.Repositorio.ProductoRepositorio;

@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private StockServicio stockServicio;
    
    public List<Producto> getAllProductos() {
        return productoRepositorio.findAll();
    }

    public Optional<Producto> getProductoById(int id) {
        return productoRepositorio.findById(id);
    }

    public Producto createProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public Producto updateProducto(Integer id, Producto producto) {
        Optional<Producto> productoTemp = productoRepositorio.findById(id);
        if (productoTemp.isPresent()) {
            Producto productoActualizar = productoTemp.get();
            productoActualizar.setNombreProducto(producto.getNombreProducto());
            productoActualizar.setPrecioProducto(producto.getPrecioProducto());
            productoActualizar.setCategoriaProducto(producto.getCategoriaProducto());
            productoActualizar.setSucursal(producto.getSucursal());
            productoActualizar.setIdCarrito(producto.getIdCarrito());
            productoActualizar.setStock(producto.getStock());
            return productoRepositorio.save(productoActualizar);
        }
        throw new RuntimeException("No se encontró el producto");
    }

    public Producto aumentarStock(Integer id, Integer cantidad) {
        Optional<Producto> productoTemp = productoRepositorio.findById(id);
        if (productoTemp.isPresent()) {
            Producto productoActualizar = productoTemp.get();
            Stock stockActualizar = productoActualizar.getStock();
            stockServicio.aumentarStock(stockActualizar.getIdStock(),cantidad);
            productoActualizar.setStock(stockActualizar);
            return productoRepositorio.save(productoActualizar);
        }
        throw new RuntimeException("No se encontró el producto");
    }

    public void deleteProducto(int id) {
        productoRepositorio.deleteById(id);
    }
}
