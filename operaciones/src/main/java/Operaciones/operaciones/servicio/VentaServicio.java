package Operaciones.operaciones.servicio;

import Operaciones.operaciones.modelo.Venta;
import Operaciones.operaciones.repositorio.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServicio {
    @Autowired
    private VentaRepositorio ventaRepositorio;

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
        if (ventaRepositorio.existsById(id)) {
            venta.setIdVenta(id);
            return ventaRepositorio.save(venta);
        }
        throw new RuntimeException("No se encontró la venta");
    }

    public void deleteVenta(int id) {
        ventaRepositorio.deleteById(id);
    }
}