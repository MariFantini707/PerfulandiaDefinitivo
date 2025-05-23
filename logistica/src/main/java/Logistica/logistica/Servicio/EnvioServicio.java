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

    public List<Envio> listarEnvios() {
        return envioRepositorio.findAll();
    }

    public Optional<Envio> obtenerEnvioPorId(Integer id) {
        return envioRepositorio.findById(id);
    }

    public Envio guardarEnvio(Envio envio) {
        return envioRepositorio.save(envio);
    }

    public Envio actualizarEnvio(Envio envio) {
        return envioRepositorio.save(envio);
    }

    public void eliminarEnvio(Integer id) {
        envioRepositorio.deleteById(id);
    }
}
