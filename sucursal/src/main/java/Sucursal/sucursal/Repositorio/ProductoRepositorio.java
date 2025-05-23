package Sucursal.sucursal.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import Sucursal.sucursal.Modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {}
