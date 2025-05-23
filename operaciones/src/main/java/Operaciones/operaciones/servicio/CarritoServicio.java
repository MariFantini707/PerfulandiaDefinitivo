package Operaciones.operaciones.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Operaciones.operaciones.modelo.Carrito;
import Operaciones.operaciones.modelo.UsuarioDto;
import Operaciones.operaciones.repositorio.CarritoRepositorio;

@Service
public class CarritoServicio {
    @Autowired
    private CarritoRepositorio carritosRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    public List<Carrito> getAllcarritos() {
        return carritosRepositorio.findAll();
    }

    public Optional<Carrito> getcarritosById(int id) {
        return carritosRepositorio.findById(id);
    }

    public Carrito updatecarritos(Integer id, Carrito carritos) {
        Optional<Carrito> carritoTemp = carritosRepositorio.findById(id);
        if (carritoTemp.isPresent()) {
            Carrito carrito = carritoTemp.get();
            carrito.setEstadocarrito(carritos.getEstadocarrito());
            carrito.setNombreCliente(carritos.getNombreCliente());
            carrito.setIdProducto(carritos.getIdProducto());
            UsuarioDto usuarioDto = UsuarioServicio.obtenerProductoPorId(carritos.getIdProducto());

            if (usuarioDto != null) {
                carrito.setTotalcarrito(UsuarioDto.getPrecioProducto());
            }

            if (carritos.getFechacarrito() == null) {
                carrito.setFechacarrito(LocalDateTime.now());
            } else {
                carrito.setFechacarrito(carritos.getFechacarrito());
            }
            return carritosRepositorio.guardar(carrito);
        }
        throw new RuntimeException("No se encontro el id del carrito de compras");
    }

    public void deleteCarritos(int id) {
        carritosRepositorio.deleteById(id);
    }


    public Carrito createCarrito(Carrito carrito) {
        UsuarioDto UsuarioDto = usuarioServicio.obtenerProductoPorId(carritos.getIdUsuario());
        if (UsuarioDto == null) {
            throw new IllegalStateException("No existe el usuario con el id: " + carritos.getIdUsuario());
        }
        UsuarioDto.setStockProducto(UsuarioDto.getStockProducto() - 1);
        productosService.actualizarProducto(UsuarioDto);
        carrito.setTotalcarrito(UsuarioDto.getPrecioProducto());
        if (carrito.getFechaCarrito() == null) {
            carrito.setFechaCarrito(LocalDateTime.now());
        }
        Carrito Carrito = carritosRepositorio.save(carritos);
        return Carrito;
    }
}
