package io.github.moaresoliveira.api.controller;

import io.github.moaresoliveira.api.model.Cliente;
import io.github.moaresoliveira.api.model.form.ClienteForm;
import io.github.moaresoliveira.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos() {
        List<Cliente> clientes = clienteService.buscarTodos();
        return clientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody ClienteForm clienteForm) {
        Cliente cliente = clienteService.salvar(clienteForm);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody ClienteForm clienteForm) {
        Cliente cliente = clienteService.atualizar(id, clienteForm);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}
