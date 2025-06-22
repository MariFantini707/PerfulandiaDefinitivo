package Administracion.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;
import Administracion.administracion.Modelo.Usuario;
import Administracion.administracion.Repositorio.UsuarioRepositorio;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(faker.name().fullName());
            usuario.setRutUsuario(faker.idNumber().valid());
            usuario.setCorreoUsuario(faker.internet().emailAddress());
            usuario.setRolUsuario(faker.job().position());
            usuarioRepositorio.save(usuario);
        }
    }
}
