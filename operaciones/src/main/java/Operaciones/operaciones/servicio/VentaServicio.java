package Operaciones.operaciones.servicio;

import Operaciones.operaciones.modelo.Venta;
import Operaciones.operaciones.repositorio.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import Operaciones.operaciones.modelo.UsuarioDto;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServicio {
    @Autowired
    private VentaRepositorio ventaRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    public List<Venta> getAllVentas() {
        return ventaRepositorio.findAll();
    }

    public Optional<Venta> getVentaById(int id) {
        return ventaRepositorio.findById(id);
    }

    public Venta createVenta(Venta venta) {
        return ventaRepositorio.save(venta);
    }

    public Venta updateVenta(Integer id, Venta venta) {
        Optional<Venta> ventaTemp = ventaRepositorio.findById(id);
        if (ventaTemp.isPresent()) {
            Venta ventaActualizar = ventaTemp.get();
            ventaActualizar.setFechaVenta(venta.getFechaVenta());
            ventaActualizar.setTotalVenta(venta.getTotalVenta());
            ventaActualizar.setCarrito(venta.getCarrito());
            ventaActualizar.setIdUsuario(venta.getIdUsuario());
            return ventaRepositorio.save(ventaActualizar);
        }
        throw new RuntimeException("No se encontró la venta");
    }

    public void deleteVenta(int id) {
        ventaRepositorio.deleteById(id);
    }

    //NUEVO METODO PARA VENTAS Y USUARIO -MF

    public UsuarioDto obtenerUsuarioDeVenta(Integer idVenta) {
        Venta venta = ventaRepositorio.findById(idVenta)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        String url = "http://localhost:8081/usuario/" + venta.getIdUsuario(); 
        return restTemplate.getForObject(url, UsuarioDto.class);
    }


}