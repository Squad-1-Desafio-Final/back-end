package br.com.acabouMony.exception;

public class CartaoNaoEncontrado extends RuntimeException{
    public CartaoNaoEncontrado(String e){
        super(e);
    }
}
