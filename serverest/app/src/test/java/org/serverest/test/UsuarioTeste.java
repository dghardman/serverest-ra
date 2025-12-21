package org.serverest.test;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.serverest.controller.Usuario;
import org.serverest.factory.IdFactory;
import org.serverest.factory.UsuarioFactory;
import org.serverest.model.UsuarioDTO;
import org.serverest.util.Ambiente;
import org.serverest.util.Mensagem;

public class UsuarioTeste {
    private String ambiente;

    @Before
    public void definirAmbiente() {
        ambiente = Ambiente.localhost;
    }

    @Test
    public void validarListagem() {
        //Validar listagem de usuários
        Usuario.listar(HttpStatus.SC_OK, ambiente);
    }

    @Test
    public void validarCadastro() {
        UsuarioDTO admin = UsuarioFactory.criar("teste", "true");

        //Validar cadastro com sucesso
        admin.setId(Usuario.cadastrar(admin, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        //Validar cadastro com email duplicado
        Usuario.cadastrar(admin, HttpStatus.SC_BAD_REQUEST, Mensagem.emailUtilizado, ambiente);
    }

    @Test
    public void validarExclusao() {
        UsuarioDTO admin = UsuarioFactory.criar("teste", "true");
        admin.setId(Usuario.cadastrar(admin, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        //Validar exclusão de usuário existente
        Usuario.excluir(admin, HttpStatus.SC_OK, Mensagem.excluidoSucesso, ambiente);

        //Validar exclusão de usuário inexistente
        Usuario.excluir(admin, HttpStatus.SC_OK, Mensagem.nenhumRegistroExcluido, ambiente);
    }

    @Test
    public void validarEdicao() {
        UsuarioDTO admin = UsuarioFactory.criar("teste", "true");
        UsuarioDTO admin2 = UsuarioFactory.criar("teste", "true");
        admin.setId(Usuario.cadastrar(admin, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        //Validar edição de usuário existente
        Usuario.editar(admin.getId(), admin, HttpStatus.SC_OK, Mensagem.alteradoSucesso, ambiente);

        //Validar edição de usuário inexistente
        String id = IdFactory.criar(16);
        Usuario.editar(id, admin2, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente);

        //Validar edição de usuário com email duplicado
        admin.setEmail(admin2.getEmail());
        Usuario.editar(admin.getId(), admin, HttpStatus.SC_BAD_REQUEST, Mensagem.emailUtilizado, ambiente);
    }

    @Test
    public void validarBuscarPorId() {
        UsuarioDTO admin = UsuarioFactory.criar("teste", "true");
        admin.setId(Usuario.cadastrar(admin, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        //Validar busca de usuário por id
        Usuario.buscarPorId(admin, HttpStatus.SC_OK, ambiente);
    }
}