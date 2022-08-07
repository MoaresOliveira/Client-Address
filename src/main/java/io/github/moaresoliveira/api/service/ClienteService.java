package io.github.moaresoliveira.api.service;

import io.github.moaresoliveira.api.model.Cliente;
import io.github.moaresoliveira.api.model.form.ClienteForm;

import java.util.List;

public interface ClienteService {

    List<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    Cliente salvar(ClienteForm cliente);

    Cliente atualizar(Long id,ClienteForm cliente);

    void deletar(Long id);

}
