package Operaciones.operaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;
import java.util.List;
import Operaciones.operaciones.modelo.Carrito;
import Operaciones.operaciones.modelo.Venta;
import Operaciones.operaciones.modelo.Resena;
import Operaciones.operaciones.repositorio.CarritoRepositorio;
import Operaciones.operaciones.repositorio.VentaRepositorio;
import Operaciones.operaciones.repositorio.ResenaRepositorio;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CarritoRepositorio carritoRepositorio;
    @Autowired
    private VentaRepositorio ventaRepositorio;
    @Autowired
    private ResenaRepositorio resenaRepositorio;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        // Carritos
        for (int i = 0; i < 10; i++) {
            Carrito carrito = new Carrito();
            carrito.setCantidadCarrito(faker.number().numberBetween(1, 10));
            carritoRepositorio.save(carrito);
        }
        List<Carrito> carritos = carritoRepositorio.findAll();
        for (int i = 0; i < carritos.size(); i++) {
            Venta venta = new Venta();
            venta.setCarrito(carritos.get(i)); // cada venta con un carrito distinto
            venta.setFechaVenta(java.sql.Date.valueOf(java.time.LocalDate.now().minusDays(faker.number().numberBetween(1, 1000))));
            venta.setIdUsuario(faker.number().numberBetween(1, 10));
            venta.setTotalVenta(faker.number().numberBetween(1000, 100000));
            ventaRepositorio.save(venta);
        }
        // Reseñas
        for (int i = 0; i < 10; i++) {
            Resena resena = new Resena();
            resena.setComentarioResena(faker.lorem().sentence());
            resena.setPuntuacionResena(faker.number().numberBetween(1, 5));
            resena.setIdUsuario(faker.number().numberBetween(1, 10));
            resenaRepositorio.save(resena);
        }
    }
}
