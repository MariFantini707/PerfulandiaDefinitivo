package Sucursal.sucursal.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import Sucursal.sucursal.Modelo.Stock;

public interface StockRepositorio extends JpaRepository<Stock, Integer> {}
