package com.meuprojeto.gestaoEscolar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

public record AlunoDto(@NotBlank String name,
                       @Email String email,
                       @Past LocalDate dataNascimento,
                       EnderecoDto endereco,
                       List<Long> disciplinasIds) {
}
