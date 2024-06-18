package com.GraphiFlow.project_PSC.services;

import com.GraphiFlow.project_PSC.entities.User;
import com.GraphiFlow.project_PSC.entities.UserAdm;
import com.GraphiFlow.project_PSC.repositories.UserAdmRepository;
import com.GraphiFlow.project_PSC.services.exceptions.DatabaseException;
import com.GraphiFlow.project_PSC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Classe de serviço para operações relacionadas a UserAdm
@Service
public class UserAdmService {

    // Injeção de dependência do repositório UserAdmRepository
    @Autowired
    private UserAdmRepository repository;

    // Método para buscar todos os usuários administrativos
    public List<UserAdm> findAll() {
        // Chama o método findAll() do repositório para obter a lista de usuários administrativos
        return repository.findAll();
    }

    // Método para buscar um usuário administrativo por ID
    public UserAdm findById(Long id) {
        // Chama o método findById() do repositório para obter o usuário administrativo com o ID fornecido
        // Se não encontrar, lança uma exceção ResourceNotFoundException
        Optional<UserAdm> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método para inserir um novo usuário administrativo
    public UserAdm insert(UserAdm obj) {
        // Salva o novo usuário administrativo no repositório
        return repository.save(obj);
    }

    // Método para excluir um usuário administrativo por ID
    public void delete(Long id) {
        try {
            // Tenta excluir o usuário administrativo com o ID fornecido
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Se o usuário administrativo não for encontrado, lança uma exceção ResourceNotFoundException
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            // Se ocorrer uma violação de integridade de dados, lança uma exceção DatabaseException
            throw new DatabaseException(e.getMessage());
        }
    }

    // Método para atualizar um usuário administrativo existente
    public UserAdm update(Long id, UserAdm obj) {
        try {
            // Obtém o usuário administrativo existente com o ID fornecido
            UserAdm entity = repository.getOne(id);
            // Atualiza os dados do usuário administrativo existente com os dados fornecidos
            updateData(entity, obj);
            // Salva as alterações no repositório
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            // Se o usuário administrativo não for encontrado, lança uma exceção ResourceNotFoundException
            throw new ResourceNotFoundException(id);
        }
    }

    // Método auxiliar para atualizar os dados do usuário administrativo
    private void updateData(UserAdm entity, UserAdm obj) {
        // Atualiza os campos do usuário administrativo com os valores fornecidos
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setSenha(obj.getSenha());
    }

    // Método para autenticar um usuário administrador com base no email e senha fornecidos
    public UserAdm authenticate(String email, String senha) {
        // Obtém todos os usuários admnistradores do repositório
        List<UserAdm> usersAdms = repository.findAll();
        // Itera sobre os usuários admnistradores para encontrar um com o email e senha correspondentes
        for (UserAdm userAdm : usersAdms) {
            if (userAdm.getEmail().equals(email) && userAdm.getSenha().equals(senha)) {
                // Se encontrar, retorna o usuário admnistrador autenticado
                return userAdm;
            }
        }
        // Se não encontrar, lança uma exceção ResourceNotFoundException
        throw new ResourceNotFoundException("Email ou senha inválida");
    }

}
