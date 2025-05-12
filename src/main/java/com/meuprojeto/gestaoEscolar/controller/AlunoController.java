package com.meuprojeto.gestaoEscolar.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meuprojeto.gestaoEscolar.dto.AlunoDto;
import com.meuprojeto.gestaoEscolar.dto.AlunoResponseDTO;
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
    public ResponseEntity<AlunoResponseDTO> criarAluno(@RequestBody @Valid AlunoDto alunoDto){
        return ResponseEntity.ok(service.salvar(alunoDto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarAluno(@PathVariable Long id){
       return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> mostrarAlunos(){
        return ResponseEntity.ok(service.mostrarTodosAlunos());
    }

    @DeleteMapping("/{id}")
    public String deletarAluno(@PathVariable Long id){
       service.deleteAluno(id);
       ResponseEntity.noContent().build();
       return "deletado com sucesso";
    }


    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO>alterarAluno(@PathVariable Long id , @RequestBody @Valid AlunoDto alunoDto){
        return ResponseEntity.ok(service.alterarAluno(id, alunoDto));

    }
}
