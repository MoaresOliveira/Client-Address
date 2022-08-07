package io.github.moaresoliveira.api.service.impl;

import feign.FeignException;
import io.github.moaresoliveira.api.exception.ClienteNotFoundException;
import io.github.moaresoliveira.api.exception.EnderecoNotFoundException;
import io.github.moaresoliveira.api.model.Cliente;
import io.github.moaresoliveira.api.model.Endereco;
import io.github.moaresoliveira.api.model.form.ClienteForm;
import io.github.moaresoliveira.api.repository.ClienteRepository;
import io.github.moaresoliveira.api.repository.EnderecoRepository;
import io.github.moaresoliveira.api.service.ClienteService;
import io.github.moaresoliveira.api.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow((() -> new ClienteNotFoundException(id)));
    }

    @Override
    public Cliente salvar(ClienteForm clienteForm) {
        Cliente cliente = new Cliente();
        setCliente(cliente, clienteForm);
        return persisteCliente(cliente);
    }

    @Override
    public Cliente atualizar(Long id,ClienteForm clienteForm) {
        Cliente clienteSalvo = buscarPorId(id);
        setCliente(clienteSalvo, clienteForm);
        return persisteCliente(clienteSalvo);
    }

    @Override
    public void deletar(Long id) {
        Cliente clienteSalvo = buscarPorId(id);
        clienteRepository.delete(clienteSalvo);
    }

    private void setCliente(Cliente cliente, ClienteForm clienteForm) {
        cliente.setNome(clienteForm.getNome());
        cliente.setEndereco(buscaEndereco(clienteForm.getCep()));
    }

    private Cliente persisteCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    private Endereco buscaEndereco(String cep) {
        if(cep.length() > 8){
            throw new IllegalArgumentException("CEP inválido. O CEP deve conter 8 dígitos.");
        }
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.buscarPorCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        if(endereco == null) {
            throw new EnderecoNotFoundException(cep);
        }
        return endereco;
    }
}
