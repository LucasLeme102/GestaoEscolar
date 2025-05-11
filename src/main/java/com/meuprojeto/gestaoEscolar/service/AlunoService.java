package com.meuprojeto.gestaoEscolar.service;

import com.meuprojeto.gestaoEscolar.dto.AlunoDto;
import com.meuprojeto.gestaoEscolar.dto.EnderecoDto;
import com.meuprojeto.gestaoEscolar.entity.Aluno;
import com.meuprojeto.gestaoEscolar.entity.Endereco;
import com.meuprojeto.gestaoEscolar.repository.AlunoRepository;
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

    public Aluno salvar(AlunoDto dtoAluno){
        Endereco endereco = Endereco.builder()
                .logradouro(dtoAluno.endereco().logradouro())
                .numero(dtoAluno.endereco().numero())
                .cidade(dtoAluno.endereco().cidade())
                .estado(dtoAluno.endereco().Estado())
                .build();
        Aluno aluno = Aluno.builder()
                .nome(dtoAluno.name())
                .email(dtoAluno.email())
                .dataNascimento(dtoAluno.dataNascimento())
                .endereco(endereco)
                .build();
        return alunoRepository.save(aluno);
    }

    public Aluno buscarPorId(Long id){
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("aluno não encontrado"));
    }

    public List<Aluno> mostrarTodosAlunos(){
        return alunoRepository.findAll();
    }

    public void deleteAluno( Long id){
        if(!alunoRepository.existsById(id)){
            throw new RuntimeException("Aluno nao encontrado");
        }
        alunoRepository.deleteById(id);
    }

    public Aluno alterarAluno(Long id, AlunoDto alunoDto){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("aluno não encontrado"));
        aluno.setNome(alunoDto.name());
        aluno.setEmail(alunoDto.email());
        aluno.setDataNascimento(alunoDto.dataNascimento());

        Endereco endereco = aluno.getEndereco();
        endereco.setLogradouro(alunoDto.endereco().logradouro());
        endereco.setCidade(alunoDto.endereco().cidade());
        endereco.setNumero(alunoDto.endereco().numero());
        endereco.setEstado(alunoDto.endereco().Estado());

        aluno.setEndereco(endereco);

        return alunoRepository.save(aluno);


    }
}


