package Logistica.logistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;
import java.util.Random;
import Logistica.logistica.Modelo.Proveedor;
import Logistica.logistica.Modelo.Pedido;
import Logistica.logistica.Modelo.Envio;
import Logistica.logistica.Repositorio.ProveedorRepositorio;
import Logistica.logistica.Repositorio.PedidoRepositorio;
import Logistica.logistica.Repositorio.EnvioRepositorio;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private EnvioRepositorio envioRepositorio;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        // Proveedores
        for (int i = 0; i < 5; i++) {
            Proveedor proveedor = new Proveedor();
            proveedor.setNombreProveedor(faker.company().name());
            proveedor.setTelefonoProveedor(faker.number().numberBetween(1000000, 9999999));
            proveedor.setCorreoProveedor(faker.internet().emailAddress());
            proveedor.setDireccionProveedor(faker.address().streetAddress());
            proveedorRepositorio.save(proveedor);
        }
        // Envios
        for (int i = 0; i < 5; i++) {
            Envio envio = new Envio();
            envio.setEstadoEnvio(faker.commerce().promotionCode());
            envioRepositorio.save(envio);
        }
        // Pedidos
        for (int i = 0; i < 10; i++) {
            Pedido pedido = new Pedido();
            pedido.setFechaPedido(java.time.LocalDate.now().minusDays(faker.number().numberBetween(1, 1000)));
            pedido.setEstadoPedido(faker.commerce().promotionCode());
            pedido.setTotalPedido(faker.number().numberBetween(1000, 100000));
            pedido.setCantidadPedido(faker.number().numberBetween(1, 20));
            pedido.setProveedor(proveedorRepositorio.findAll().get(random.nextInt((int) proveedorRepositorio.count())));
            pedido.setEnvio(envioRepositorio.findAll().get(random.nextInt((int) envioRepositorio.count())));
            pedido.setIdUsuario(faker.number().numberBetween(1, 10));
            pedido.setIdProducto(faker.number().numberBetween(1, 10));
            pedidoRepositorio.save(pedido);
        }
    }
}
