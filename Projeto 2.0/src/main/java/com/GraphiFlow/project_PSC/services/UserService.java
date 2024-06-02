package com.GraphiFlow.project_PSC.services;

import com.GraphiFlow.project_PSC.entities.User;
import com.GraphiFlow.project_PSC.repositories.UserRepository;
import com.GraphiFlow.project_PSC.services.exceptions.DatabaseException;
import com.GraphiFlow.project_PSC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Classe de serviço para operações relacionadas a User
@Service
public class UserService {

    // Injeção de dependência do repositório UserRepository
    @Autowired
    private UserRepository repository;

    // Método para buscar todos os usuários
    public List<User> findAll() {
        // Chama o método findAll() do repositório para obter a lista de usuários
        return repository.findAll();
    }

    // Método para buscar um usuário por ID
    public User findById(Long id) {
        // Tenta encontrar o usuário com o ID fornecido no repositório
        Optional<User> obj = repository.findById(id);
        // Se não encontrar, lança uma exceção ResourceNotFoundException
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método para inserir um novo usuário
    public User insert(User obj) {
        // Salva o novo usuário no repositório
        return repository.save(obj);
    }

    // Método para excluir um usuário por ID
    public void delete(Long id) {
        try {
            // Tenta excluir o usuário com o ID fornecido
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Se não encontrar o usuário, lança uma exceção ResourceNotFoundException
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            // Se houver violação de integridade de dados, lança uma exceção DatabaseException
            throw new DatabaseException(e.getMessage());
        };
    }

    // Método para atualizar um usuário existente
    public User update(Long id, User obj) {
        try {
            // Tenta obter o usuário com o ID fornecido
            User entity = repository.getReferenceById(id);
            // Atualiza os dados do usuário com base nos dados fornecidos
            updateData(entity, obj);
            // Salva as alterações no repositório
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            // Se não encontrar o usuário, lança uma exceção ResourceNotFoundException
            throw new ResourceNotFoundException(id);
        }
    }

    // Método auxiliar para atualizar os dados de um usuário
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setIdade(obj.getIdade());
        entity.setSenha(obj.getSenha());
    }

    // Método para autenticar um usuário com base no email e senha fornecidos
    public User authenticate(String email, String senha) {
        // Obtém todos os usuários do repositório
        List<User> users = repository.findAll();
        // Itera sobre os usuários para encontrar um com o email e senha correspondentes
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                // Se encontrar, retorna o usuário autenticado
                return user;
            }
        }
        // Se não encontrar, lança uma exceção ResourceNotFoundException
        throw new ResourceNotFoundException("Email ou senha inválida");
    }
}
