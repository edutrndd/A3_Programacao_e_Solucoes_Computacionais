package com.GraphiFlow.project_PSC.resources;

import com.GraphiFlow.project_PSC.entities.User;
import com.GraphiFlow.project_PSC.entities.UserAdm;
import com.GraphiFlow.project_PSC.services.UserAdmService;
import com.GraphiFlow.project_PSC.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Controlador REST para resources relacionados a UserAdm.
@RestController
@RequestMapping(value = "/useradm")
public class UserAdmResource {

    // Injeção de dependência do serviço de UserAdmService
    @Autowired
    private UserAdmService service;

    // Endpoint para buscar todos os usuários administrativos
    @GetMapping
    public ResponseEntity<List<UserAdm>> findAll() {
        // Chama o método findAll() do serviço para obter a lista de usuários
        List<UserAdm> list = service.findAll();
        // Retorna uma resposta HTTP com status 200 (OK) e a lista de usuários no corpo
        return ResponseEntity.ok().body(list);
    }

    // Endpoint para buscar um usuário administrativo por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserAdm> findById(@PathVariable Long id) {
        // Chama o método findById() do serviço para obter o usuário com o ID fornecido
        UserAdm obj = service.findById(id);
        // Retorna uma resposta HTTP com status 200 (OK) e o usuário no corpo
        return ResponseEntity.ok().body(obj);
    }

    // Endpoint para inserir um novo usuário administrativo
    @PostMapping
    public ResponseEntity<UserAdm> insert(@RequestBody UserAdm obj) {
        // Chama o método insert() do serviço para inserir o novo usuário
        obj = service.insert(obj);
        // Constrói a URI para o novo recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        // Retorna uma resposta HTTP com status 201 (Created) e o novo usuário no corpo
        return ResponseEntity.created(uri).body(obj);
    }

    // Endpoint para excluir um usuário administrativo por ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Chama o método delete() do serviço para excluir o usuário com o ID fornecido
        service.delete(id);
        // Retorna uma resposta HTTP com status 204 (No Content) indicando que a operação foi bem-sucedida
        return ResponseEntity.noContent().build();
    }

    // Endpoint para atualizar um usuário administrativo existente
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserAdm> update(@PathVariable Long id, @RequestBody UserAdm obj) {
        // Chama o método update() do serviço para atualizar o usuário com o ID fornecido
        obj = service.update(id, obj);
        // Retorna uma resposta HTTP com status 200 (OK) e o usuário atualizado no corpo
        return ResponseEntity.ok().body(obj);
    }

}