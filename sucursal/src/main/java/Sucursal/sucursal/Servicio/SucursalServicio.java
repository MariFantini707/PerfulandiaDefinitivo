package Sucursal.sucursal.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import Sucursal.sucursal.Modelo.Sucursal;
import Sucursal.sucursal.Repositorio.SucursalRepositorio;

@Service
public class SucursalServicio {
    @Autowired
    private SucursalRepositorio sucursalRepositorio;

    public List<Sucursal> getAllSucursales() {
        return sucursalRepositorio.findAll();
    }

    public Optional<Sucursal> getSucursalById(int id) {
        return sucursalRepositorio.findById(id);
    }

    public Sucursal createSucursal(Sucursal sucursal) {
        return sucursalRepositorio.save(sucursal);
    }

    public Sucursal updateSucursal(Integer id, Sucursal sucursal) {
        Optional<Sucursal> sucursalTemp = sucursalRepositorio.findById(id);
        if (sucursalTemp.isPresent()) {
            Sucursal sucursalActualizar = sucursalTemp.get();
            // Actualiza los campos necesarios aquí
            return sucursalRepositorio.save(sucursalActualizar);
        }
        throw new RuntimeException("No se encontró la sucursal");
    }

    public void deleteSucursal(int id) {
        sucursalRepositorio.deleteById(id);
    }
}
