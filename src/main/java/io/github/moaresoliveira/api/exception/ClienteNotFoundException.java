package io.github.moaresoliveira.api.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(Long id) {
        super("Cliente com id " + id + " não encontrado.");
    }

}
