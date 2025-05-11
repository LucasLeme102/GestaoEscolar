package com.meuprojeto.gestaoEscolar.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meuprojeto.gestaoEscolar.dto.AlunoDto;
import com.meuprojeto.gestaoEscolar.entity.Aluno;
import com.meuprojeto.gestaoEscolar.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody @Valid AlunoDto alunoDto){
        Aluno aluno = service.salvar(alunoDto);
        return ResponseEntity.ok(aluno);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable Long id){
        Aluno aluno = service.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> mostrarAlunos(){
        List<Aluno> alunos = service.mostrarTodosAlunos();
        return ResponseEntity.ok(alunos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id){
       service.deleteAluno(id);
       return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Aluno> alterarAluno(@PathVariable Long id , @RequestBody @Valid AlunoDto alunoDto){
        return ResponseEntity.ok(service.alterarAluno(id,alunoDto));

    }
}
