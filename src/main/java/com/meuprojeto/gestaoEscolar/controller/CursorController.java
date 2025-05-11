package com.meuprojeto.gestaoEscolar.controller;

import com.meuprojeto.gestaoEscolar.dto.CursoDto;
import com.meuprojeto.gestaoEscolar.dto.CursoResponseDTO;
import com.meuprojeto.gestaoEscolar.entity.Curso;

import com.meuprojeto.gestaoEscolar.service.CursoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursorController {

    @Autowired
    private  CursoService cursoService;


    @PostMapping
    public ResponseEntity<CursoResponseDTO> criarCurso(@RequestBody CursoDto cursoDto){
        CursoResponseDTO salvar = cursoService.salvar(cursoDto);
        return ResponseEntity.ok(salvar);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> showAll(){
        return ResponseEntity.ok(cursoService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> buscarCursoPorId(@PathVariable Long id){

        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurso(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(cursoService.deleteById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> updateCurso(@PathVariable Long id, @RequestBody CursoDto cursoDto){
        return ResponseEntity.ok(cursoService.updateById(id,cursoDto));
    }
}
