package Administracion.administracion.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import Administracion.administracion.Modelo.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    
}
