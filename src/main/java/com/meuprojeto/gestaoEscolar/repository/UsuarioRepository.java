package com.meuprojeto.gestaoEscolar.repository;

import com.meuprojeto.gestaoEscolar.entity.users.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    UserDetails findByLogin(String login);
}
