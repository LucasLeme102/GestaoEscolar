package com.meuprojeto.gestaoEscolar.entity.users;

public enum UserRole {
    RESPONSAVEIS("responsaveis"),
    ALUNO("aluno");


    private String role;

    UserRole(String role){
        this.role = role;

    }

    public String getRole() {
        return role;
    }
}
