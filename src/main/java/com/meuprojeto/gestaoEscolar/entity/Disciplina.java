package com.meuprojeto.gestaoEscolar.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "diciplinas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;


    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> aluno;
}
