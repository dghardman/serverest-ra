package org.serverest.util;

public class Endpoint {
    public static final String login = "/login";
    public static final String usuarios = "/usuarios";
    public static final String usuariosId = "/usuarios/{_id}";
    public static final String produtos = "/produtos";
    public static final String produtosId = "/produtos/{_id}";
    public static final String carrinhos = "/carrinhos";
    public static final String carrinhosId = "/carrinhos/{_id}";
    public static final String concluirCompra = "/carrinhos/concluir-compra";
    public static final String cancelarCompra = "/carrinhos/cancelar-compra";
}