package Logistica.logistica.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Logistica.logistica.Modelo.Envio;
import Logistica.logistica.Repositorio.EnvioRepositorio;

@Service
public class EnvioServicio {

    @Autowired
    private EnvioRepositorio envioRepositorio;

    // Listar todos los envíos
    public List<Envio> listarEnvios() {
        return envioRepositorio.findAll();
    }

    // Obtener un envío por ID
    public Optional<Envio> obtenerEnvioPorId(Integer id) {
        return envioRepositorio.findById(id);
    }

    // Crear nuevo envío
    public Envio guardarEnvio(Envio envio) {
        return envioRepositorio.save(envio);
    }

    // Actualizar envío
    public Envio actualizarEnvio(Envio envio) {
        return envioRepositorio.save(envio);
    }

    // Eliminar envío
    public void eliminarEnvio(Integer id) {
        envioRepositorio.deleteById(id);
    }
}
