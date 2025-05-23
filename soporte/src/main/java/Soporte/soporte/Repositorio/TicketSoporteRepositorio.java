package Soporte.soporte.Repositorio;

import Soporte.soporte.Modelo.TicketSoporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketSoporteRepositorio extends JpaRepository<TicketSoporte, Integer> {
}