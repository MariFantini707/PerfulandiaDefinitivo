package Logistica.logistica.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Logistica.logistica.Modelo.Proveedor;
import Logistica.logistica.Repositorio.ProveedorRepositorio;

@Service
public class ProveedorServicio {
    
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    public List<Proveedor> listarProveedores() {
        return proveedorRepositorio.findAll();
    }

    public Optional<Proveedor> obtenerProveedorPorId(Integer id) {
        return proveedorRepositorio.findById(id);
    }

    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    public Proveedor actualizarProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    public void eliminarProveedor(Integer id) {
        proveedorRepositorio.deleteById(id);
    }
}
