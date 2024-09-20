package com.arenavirtual.backend.security;

import com.arenavirtual.backend.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            // descriptografa o token
            String login = jwtService.validateToken(token);

            UserDetails user = userRepository.findByEmail(login);

            // cria uma autenticação com o user encontrado, caso o user for null gera um nullpointerexception,
            // porém até o momento desse comentario nao ha roles na app, entao pode ser uma 'List' vazia
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            // finalmente autentica o usuario encontrado
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // continuar o fluxo do filtro no security config
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        // token passado no postman/insomnia/etc
        String authorization = request.getHeader("Authorization");

        // se nao for passado nenhum
        if (authorization == null) {
            return null;
        }

        // retornar apenas o token
        return authorization.replace("Bearer ", "");
    }

}
