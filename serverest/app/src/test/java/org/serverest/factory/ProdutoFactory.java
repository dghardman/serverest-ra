package org.serverest.factory;

import org.serverest.model.ProdutoDTO;
import com.github.javafaker.Faker;

public class ProdutoFactory {
    public static ProdutoDTO criar(Integer preco, Integer quantidade) {
        Faker faker = new Faker();
        String nome = faker.commerce().productName();
        String descricao = "Fake Product";
        ProdutoDTO produtoDTO = new ProdutoDTO(nome, preco, descricao, quantidade);
        return produtoDTO;
    }
}