package org.serverest.factory;

import org.serverest.model.UsuarioDTO;
import com.github.javafaker.Faker;

public class UsuarioFactory {
    public static UsuarioDTO criar(String password, String administrador) {
        Faker faker = new Faker();
        String nome = faker.name().firstName();
        String email = nome.concat("@restassured.com");
        UsuarioDTO usuarioDTO = new UsuarioDTO(nome, email, password, administrador);
        return usuarioDTO;
    }
}