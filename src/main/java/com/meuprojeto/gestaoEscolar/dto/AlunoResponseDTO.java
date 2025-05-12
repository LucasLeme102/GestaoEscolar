package com.meuprojeto.gestaoEscolar.dto;

import java.time.LocalDate;
import java.util.List;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento,
        EnderecoDto enderecoDto,
        List<DisciplinasResponseDTO> disciplinas
) {}