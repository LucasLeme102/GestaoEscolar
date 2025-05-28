package com.meuprojeto.gestaoEscolar.controller;

import com.meuprojeto.gestaoEscolar.dto.AuthDto;
import com.meuprojeto.gestaoEscolar.dto.LoginResponseDto;
import com.meuprojeto.gestaoEscolar.dto.RegisterDto;
import com.meuprojeto.gestaoEscolar.entity.users.Usuario;
import com.meuprojeto.gestaoEscolar.infra.security.TokenService;
import com.meuprojeto.gestaoEscolar.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto dto){
        if(this.repository.findByLogin(dto.login()) != null) return ResponseEntity.badRequest().build();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.password());
        Usuario usuario = new Usuario(dto.login(),senhaCriptografada, dto.role());
        repository.save(usuario);
        return ResponseEntity.ok().build();

    }
}
