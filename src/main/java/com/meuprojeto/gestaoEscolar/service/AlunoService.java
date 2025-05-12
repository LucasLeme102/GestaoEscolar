package com.meuprojeto.gestaoEscolar.service;

import com.meuprojeto.gestaoEscolar.dto.*;
import com.meuprojeto.gestaoEscolar.entity.Aluno;
import com.meuprojeto.gestaoEscolar.entity.Disciplina;
import com.meuprojeto.gestaoEscolar.entity.Endereco;
import com.meuprojeto.gestaoEscolar.exceptions.AlunoNotFoundExecption;
import com.meuprojeto.gestaoEscolar.repository.AlunoRepository;
import com.meuprojeto.gestaoEscolar.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public AlunoResponseDTO salvar(AlunoDto dtoAluno ){
        Aluno aluno = new Aluno();

        aluno.setNome(dtoAluno.name());
        aluno.setEmail(dtoAluno.email());
        aluno.setDataNascimento(dtoAluno.dataNascimento());

        Endereco endereco = new Endereco();
        endereco.setLogradouro(dtoAluno.endereco().logradouro());
        endereco.setNumero(dtoAluno.endereco().numero());
        endereco.setCidade(dtoAluno.endereco().cidade());
        endereco.setEstado(dtoAluno.endereco().Estado());
        aluno.setEndereco(endereco);

        if(dtoAluno.disciplinasIds() != null && !dtoAluno.disciplinasIds().isEmpty()){
            List<Disciplina> disciplinas = disciplinaRepository.findAllById(dtoAluno.disciplinasIds());
            aluno.setDisciplinas(disciplinas);
        }
        return toResponseDto(alunoRepository.save(aluno));
    }


    public AlunoResponseDTO buscarPorId(Long id){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new AlunoNotFoundExecption());
        return toResponseDto(aluno);
    }

    public List<AlunoResponseDTO> mostrarTodosAlunos(){
        return alunoRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    public void deleteAluno( Long id){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new AlunoNotFoundExecption());
        alunoRepository.deleteById(id);
    }

    public AlunoResponseDTO alterarAluno(Long id, AlunoDto alunoDto){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(AlunoNotFoundExecption::new);
        aluno.setNome(alunoDto.name());
        aluno.setEmail(alunoDto.email());
        aluno.setDataNascimento(alunoDto.dataNascimento());

        Endereco endereco = new Endereco();

        endereco.setLogradouro(alunoDto.endereco().logradouro());
        endereco.setCidade(alunoDto.endereco().cidade());
        endereco.setNumero(alunoDto.endereco().numero());
        endereco.setEstado(alunoDto.endereco().Estado());

        aluno.setEndereco(endereco);

        if(alunoDto.disciplinasIds() != null && !alunoDto.disciplinasIds().isEmpty() ){
            List<Disciplina> disciplinas = disciplinaRepository.findAllById(alunoDto.disciplinasIds());
            aluno.setDisciplinas(disciplinas);
        }

        return toResponseDto(alunoRepository.save(aluno));


    }
    private AlunoResponseDTO toResponseDto( Aluno aluno){
        List<DisciplinasResponseDTO> disciplinasResponseDTOS = aluno.getDisciplinas()
                .stream().map(d -> new DisciplinasResponseDTO(d.getId(),d.getNome())).toList();
        EnderecoDto enderecoDto = new EnderecoDto(
                aluno.getEndereco().getLogradouro(),
                aluno.getEndereco().getNumero(),
                aluno.getEndereco().getCidade(),
                aluno.getEndereco().getEstado()
        );

        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getDataNascimento(),
                enderecoDto,disciplinasResponseDTOS

        );
    }

}


