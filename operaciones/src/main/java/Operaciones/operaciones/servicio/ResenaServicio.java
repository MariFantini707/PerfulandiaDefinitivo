package Operaciones.operaciones.servicio;

import Operaciones.operaciones.modelo.Resena;
import Operaciones.operaciones.repositorio.ResenaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaServicio {
    @Autowired
    private ResenaRepositorio resenaRepositorio;

    public List<Resena> getAllResenas() {
        return resenaRepositorio.findAll();
    }

    public Optional<Resena> getResenaById(int id) {
        return resenaRepositorio.findById(id);
    }

    public Resena createResena(Resena resena) {
        return resenaRepositorio.save(resena);
    }

    public Resena updateResena(Integer id, Resena resena) {
        Optional<Resena> resenaTemp = resenaRepositorio.findById(id);
        if (resenaTemp.isPresent()) {
            Resena resenaActualizar = resenaTemp.get();
            resenaActualizar.setComentarioResena(resena.getComentarioResena());
            resenaActualizar.setPuntuacionResena(resena.getPuntuacionResena());
            resenaActualizar.setIdUsuario(resena.getIdUsuario());
            return resenaRepositorio.save(resenaActualizar);
        }
        throw new RuntimeException("No se encontró la reseña");
    }

    public void deleteResena(int id) {
        resenaRepositorio.deleteById(id);
    }
}