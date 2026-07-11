package org.serverest.controller;

import org.serverest.model.UsuarioDTO;
import org.serverest.model.ProdutoDTO;
import io.restassured.http.ContentType;
import org.serverest.util.Endpoint;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class Produto {
    public static void listar(Integer statuscode, String ambiente) {
        when()
                .get(ambiente.concat(Endpoint.produtos))
        .then()
                .statusCode(statuscode);
    }

    public static ProdutoDTO cadastrar(ProdutoDTO produtoDTO, UsuarioDTO usuarioDTO, Integer statusCode, String mensagem, String ambiente) {
        produtoDTO.setId(given()
                .header("Authorization", usuarioDTO.getToken())
                .body("{\n" +
                        "  \"nome\": \"" + produtoDTO.getNome() + "\",\n" +
                        "  \"preco\":" + produtoDTO.getPreco() + ",\n" +
                        "  \"descricao\": \"" + produtoDTO.getDescricao() + "\",\n" +
                        "  \"quantidade\":" + produtoDTO.getQuantidade() + "\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
                .post(ambiente.concat(Endpoint.produtos))
        .then()
                .statusCode(statusCode)
                .body("message", is(mensagem))
                .extract().path("_id"));
        return produtoDTO;
    }

    public static void checarEstoque(ProdutoDTO produtoDTO, Integer quantidade, Integer statusCode, String ambiente) {
        given()
                .pathParam("_id", produtoDTO.getId())
        .when()
                .get(ambiente.concat(Endpoint.produtosId))
        .then()
                .statusCode(statusCode)
                .body("quantidade", is(quantidade));
    }

    public static void excluir(ProdutoDTO produtoDTO, UsuarioDTO usuarioDTO, Integer statusCode, String mensagem, String ambiente) {
        given()
                .pathParam("_id", produtoDTO.getId())
                .header("Authorization", usuarioDTO.getToken())
        .when()
                .delete(ambiente.concat(Endpoint.produtosId))
        .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));
    }
}