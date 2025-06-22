package Sucursal.sucursal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import Sucursal.sucursal.Repositorio.ProductoRepositorio;
import Sucursal.sucursal.Repositorio.SucursalRepositorio;
import Sucursal.sucursal.Repositorio.StockRepositorio;
import net.datafaker.Faker;
import Sucursal.sucursal.Modelo.Sucursal;
import Sucursal.sucursal.Modelo.Producto;
import Sucursal.sucursal.Modelo.Stock;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private SucursalRepositorio sucursalRepositorio;
    @Autowired
    private ProductoRepositorio productoRepositorio;
    @Autowired
    private StockRepositorio stockRepositorio;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar Sucursales
        for (int i = 0; i < 5; i++) {
            Sucursal sucursal = new Sucursal();
            sucursal.setNombreSucursal(faker.company().name());
            sucursal.setDireccionSucursal(faker.address().streetAddress());
            sucursal.setTelefonoSucursal(faker.number().numberBetween(1000000, 9999999));
            sucursalRepositorio.save(sucursal);
        }

        // Generar Stocks
        for (int i = 0; i < 10; i++) {
            Stock stock = new Stock();
            stock.setCantidad(faker.number().numberBetween(1, 100));
            // idProducto se setea después de crear productos
            stockRepositorio.save(stock);
        }

        // Generar Productos
        for (int i = 0; i < 10; i++) {
            Producto producto = new Producto();
            producto.setNombreProducto(faker.commerce().productName());
            producto.setPrecioProducto(faker.number().numberBetween(1000, 100000));
            producto.setCategoriaProducto(faker.commerce().department());
            // Asignar sucursal aleatoria
            Sucursal sucursal = sucursalRepositorio.findAll().get(random.nextInt((int) sucursalRepositorio.count()));
            producto.setSucursal(sucursal);
            // Asignar idCarrito aleatorio (dummy)
            producto.setIdCarrito(faker.number().numberBetween(1, 10));
            // Asignar stock aleatorio
            Stock stock = stockRepositorio.findAll().get(random.nextInt((int) stockRepositorio.count()));
            producto.setStock(stock);
            productoRepositorio.save(producto);
            // Actualizar idProducto en stock
            stock.setIdProducto(producto.getIdProducto());
            stockRepositorio.save(stock);
        }
    }
}
