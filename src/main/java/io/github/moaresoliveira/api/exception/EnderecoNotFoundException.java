package io.github.moaresoliveira.api.exception;

public class EnderecoNotFoundException extends RuntimeException {

    public EnderecoNotFoundException(String cep) {
        super("Endereco com cep " + cep + " não encontrado.");
    }

}
