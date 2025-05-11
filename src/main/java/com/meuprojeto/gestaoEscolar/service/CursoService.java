package com.meuprojeto.gestaoEscolar.service;

import com.meuprojeto.gestaoEscolar.dto.CursoDto;
import com.meuprojeto.gestaoEscolar.dto.CursoResponseDTO;
import com.meuprojeto.gestaoEscolar.dto.DisciplinaDto;
import com.meuprojeto.gestaoEscolar.dto.DisciplinasResponseDTO;
import com.meuprojeto.gestaoEscolar.entity.Curso;
import com.meuprojeto.gestaoEscolar.entity.Disciplina;
import com.meuprojeto.gestaoEscolar.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoResponseDTO salvar(CursoDto dto){
        Curso curso = new Curso();
        curso.setName(dto.nome());
        List<Disciplina> disciplinas = dto.disciplinas()
                .stream()
                .map(d -> {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setNome(d.nome());
                    disciplina.setCurso(curso);
                    return disciplina;
                }).toList();
        curso.setDisciplinas(disciplinas);
        return toResponseDto(cursoRepository.save(curso));
    }

    public List<CursoResponseDTO> buscarTodos(){
        List<Curso> all = cursoRepository.findAll();
        List<CursoResponseDTO> lista = new ArrayList<>();
        for (Curso x : all){
            lista.add(toResponseDto(x));
        }
        return lista;
//        return cursoRepository.findAll()
//                .stream()
//                .map(this::toResponseDto)
//                .toList();
    }


    public CursoResponseDTO buscarPorId(Long id){
       Curso curso = cursoRepository.findById(id).orElseThrow( () -> new RuntimeException("Curso nao encontrado"));
        return toResponseDto(curso);
    }

    public String deleteById(Long id){
        Curso curso = cursoRepository.findById(id).orElseThrow( () -> new RuntimeException("Curso nao encontrado"));
        cursoRepository.deleteById(id);
        return "Deletado com sucesso";
    }

    public CursoResponseDTO updateById(Long id, CursoDto cursoDto){
        Curso curso = cursoRepository.findById(id).orElseThrow( () -> new RuntimeException("Curso nao encontrado"));

        curso.setName(cursoDto.nome());
        curso.getDisciplinas().clear();
        List<DisciplinaDto> disciplinasDto = cursoDto.disciplinas() != null ? cursoDto.disciplinas() : List.of();
        disciplinasDto.forEach(d -> {
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(d.nome());
            disciplina.setCurso(curso);
            curso.getDisciplinas().add(disciplina);
        });

        return toResponseDto(cursoRepository.save(curso));


    }

    private CursoResponseDTO toResponseDto(Curso curso){
        List<DisciplinasResponseDTO> disciplinas = curso.getDisciplinas()
                .stream()
                .map(d -> new DisciplinasResponseDTO(d.getId(), d.getNome()))
                .toList();

        return new CursoResponseDTO(curso.getId(), curso.getName(), disciplinas);
    }



}
