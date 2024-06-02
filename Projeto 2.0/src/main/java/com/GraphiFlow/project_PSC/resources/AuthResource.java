package com.GraphiFlow.project_PSC.resources;

import com.GraphiFlow.project_PSC.entities.User;
import com.GraphiFlow.project_PSC.entities.UserAdm;
import com.GraphiFlow.project_PSC.services.UserAdmService;
import com.GraphiFlow.project_PSC.services.UserService;
import com.GraphiFlow.project_PSC.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth") // Define o mapeamento base para todos os endpoints deste controlador
public class AuthResource {

    @Autowired // Injeção de dependência para o UserService
    private UserService userService;

    @Autowired // Injeção de dependência para o UserAdmService
    private UserAdmService userAdmService;

    @PostMapping("/login") // Mapeia requisições POST para /auth/login
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email"); // Extrai o email dos dados de login fornecidos
        String senha = loginData.get("senha"); // Extrai a senha dos dados de login fornecidos

        // Cria um mapa para armazenar a resposta
        Map<String, Object> response = new HashMap<>();
        try {
            // Tenta autenticar como User
            User user = userService.authenticate(email, senha);
            response.put("success", true); // Adiciona um indicador de sucesso à resposta
            response.put("user", user); // Adiciona o usuário autenticado à resposta
            response.put("userType", "USER"); // Adiciona o tipo de usuário à resposta
            return ResponseEntity.ok().body(response); // Retorna a resposta HTTP com status 200 (OK)
        } catch (ResourceNotFoundException e1) {
            // Se a autenticação como User falhar, tenta autenticar como UserAdm
            try {
                UserAdm userAdm = userAdmService.authenticate(email, senha);
                response.put("success", true); // Adiciona um indicador de sucesso à resposta
                response.put("userAdm", userAdm); // Adiciona o usuário administrador autenticado à resposta
                response.put("userType", "ADMIN"); // Adiciona o tipo de usuário à resposta
                return ResponseEntity.ok().body(response); // Retorna a resposta HTTP com status 200 (OK)
            } catch (ResourceNotFoundException e2) {
                // Se a autenticação como UserAdm também falhar, retorna uma resposta de erro
                response.put("success", false); // Adiciona um indicador de falha à resposta
                response.put("message", "Email ou senha incorretos. Tente novamente."); // Adiciona uma mensagem de erro à resposta
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Retorna a resposta HTTP com status 401 (Unauthorized)
            }
        }
    }
}
