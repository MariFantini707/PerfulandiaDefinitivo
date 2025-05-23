package Operaciones.operaciones.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import Operaciones.operaciones.modelo.Carrito;

public interface CarritoRepositorio extends JpaRepository<Carrito, Integer> {}
