package Sucursal.sucursal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import Sucursal.sucursal.Repositorio.ProductoRepositorio;
import Sucursal.sucursal.Repositorio.SucursalRepositorio;
import Sucursal.sucursal.Repositorio.StockRepositorio;
import net.datafaker.Faker;
import Sucursal.sucursal.Modelo.Sucursal;
import Sucursal.sucursal.Modelo.Producto;
import Sucursal.sucursal.Modelo.Stock;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Profile("dev")
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
        List<Sucursal> sucursales = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Sucursal sucursal = new Sucursal();
            sucursal.setNombreSucursal(faker.company().name());
            sucursal.setDireccionSucursal(faker.address().streetAddress());
            sucursal.setTelefonoSucursal(faker.number().numberBetween(1000000, 9999999));
            sucursalRepositorio.save(sucursal);
            sucursales.add(sucursal);
        }

        // Generar Productos y Stocks correctamente enlazados
        for (int i = 0; i < 10; i++) {
            // Crear stock primero con idProducto dummy
            Stock stock = new Stock();
            stock.setCantidad(faker.number().numberBetween(1, 100));
            stock.setIdProducto(0); // dummy temporal
            stockRepositorio.save(stock);

            // Crear producto y enlazar stock
            Producto producto = new Producto();
            producto.setNombreProducto(faker.commerce().productName());
            producto.setPrecioProducto(faker.number().numberBetween(1000, 100000));
            producto.setCategoriaProducto(faker.commerce().department());
            Sucursal sucursal = sucursales.get(random.nextInt(sucursales.size()));
            producto.setSucursal(sucursal);
            producto.setIdCarrito(faker.number().numberBetween(1, 10));
            producto.setStock(stock);
            productoRepositorio.save(producto);

            // Actualizar stock con idProducto real
            stock.setIdProducto(producto.getIdProducto());
            stockRepositorio.save(stock);
        }
    }
}
