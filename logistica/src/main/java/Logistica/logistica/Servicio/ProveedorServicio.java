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

    // Obtener todos los proveedores
    public List<Proveedor> listarProveedores() {
        return proveedorRepositorio.findAll();
    }

    // Obtener un proveedor por ID
    public Optional<Proveedor> obtenerProveedorPorId(Integer id) {
        return proveedorRepositorio.findById(id);
    }

    // Guardar un proveedor nuevo
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    // Actualizar proveedor existente
    public Proveedor actualizarProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    // Eliminar proveedor por ID
    public void eliminarProveedor(Integer id) {
        proveedorRepositorio.deleteById(id);
    }
}
