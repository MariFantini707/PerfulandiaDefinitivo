package Operaciones.operaciones.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Operaciones.operaciones.modelo.Carrito;
import Operaciones.operaciones.repositorio.CarritoRepositorio;

@Service
public class CarritoServicio {
    @Autowired
    private CarritoRepositorio carritosRepositorio;

    public List<Carrito> getAllCarritos() {
        return carritosRepositorio.findAll();
    }

    public Optional<Carrito> getCarritosById(int id) {
        return carritosRepositorio.findById(id);
    }

    public Carrito updateCarritos(Integer id, Carrito carritos) {
        Optional<Carrito> carritoTemp = carritosRepositorio.findById(id);
        if (carritoTemp.isPresent()) {
            Carrito carrito = carritoTemp.get();
            carrito.setCantidadCarrito(carritos.getCantidadCarrito());
            return carritosRepositorio.save(carrito);
        }
        throw new RuntimeException("No se encontro el id del carrito de compras");
    }

    public void deleteCarritos(int id) {
        carritosRepositorio.deleteById(id);
    }


    public Carrito createCarrito(Carrito carrito) {
        Carrito Carrito = carritosRepositorio.save(carrito);
        return Carrito;
    }
}
