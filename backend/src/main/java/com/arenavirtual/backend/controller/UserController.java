package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.dto.LoginDTO;
import com.arenavirtual.backend.dto.PlayerDTO;
import com.arenavirtual.backend.dto.UserDTO;
import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO dto) {
        if (userService.existsByUsernameOrEmail(dto.username(), dto.email())) {
            return ResponseEntity.badRequest().body("Usuário ou email já existente!");
        }

        if (!dto.password().equals(dto.confirmPassword())) {
            return ResponseEntity.badRequest().body("Senhas não são iguais!");
        }

        String encode = new BCryptPasswordEncoder().encode(dto.password());

        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);
        newUser.setPassword(encode);
        userService.save(newUser);

        return ResponseEntity.ok("Usuário criado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO dto) {
        // faz uma "preparação" com os dados de user e password para depois autenticar de fato
        UsernamePasswordAuthenticationToken userPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

        // autenticar o usuario baseado no user e password passados acima, que retorna o USER efetivamente com info de autenticação
        Authentication authenticate = this.authenticationManager.authenticate(userPassword);

        return ResponseEntity.ok("login feito");
    }

    @PostMapping("/player")
    public ResponseEntity<String> becomePlayer(@RequestBody PlayerDTO dto) {
        Optional<User> userFound = userService.findByUsernameOrEmail(dto.username(), dto.userEmail());

        if (userFound.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado!");
        }

        Player userToPlayer = new Player();
        BeanUtils.copyProperties(dto, userToPlayer);
        userToPlayer.setUser(userFound.get());

        userService.createPlayer(userToPlayer);

        return ResponseEntity.ok().body(userToPlayer.toString());
    } 

}
