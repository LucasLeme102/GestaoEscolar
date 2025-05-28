package com.meuprojeto.gestaoEscolar.dto;

import com.meuprojeto.gestaoEscolar.entity.users.UserRole;

public record RegisterDto(String login, String password, UserRole role ) {



}
