package com.meuprojeto.gestaoEscolar.dto;


import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Set;

public record CursoDto(String nome,
                       List<DisciplinaDto> disciplinas) {
}
