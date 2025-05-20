package br.com.acabouMony.exception;

public class ProdutoNaoEncontrado extends RuntimeException {
    public ProdutoNaoEncontrado(String message) {
        super(message);
    }
}
