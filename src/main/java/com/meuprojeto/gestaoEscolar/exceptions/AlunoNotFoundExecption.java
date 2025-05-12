package com.meuprojeto.gestaoEscolar.exceptions;

public class AlunoNotFoundExecption extends RuntimeException {
    public AlunoNotFoundExecption(String message) {
        super(message);
    }
    public AlunoNotFoundExecption() {
        super("Aluno nao encontrado ");
    }
}
