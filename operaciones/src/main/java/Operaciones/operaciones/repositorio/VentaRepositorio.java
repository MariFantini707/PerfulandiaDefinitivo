package Operaciones.operaciones.repositorio;

import Operaciones.operaciones.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepositorio extends JpaRepository<Venta, Integer> {
}