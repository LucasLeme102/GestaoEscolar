package com.meuprojeto.gestaoEscolar.dto;

import java.util.List;
import java.util.Set;

public record CursoResponseDTO(Long id,
                               String nome,
                               List<DisciplinasResponseDTO> disciplinas) {
}
