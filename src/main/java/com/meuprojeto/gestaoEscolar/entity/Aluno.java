package com.meuprojeto.gestaoEscolar.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "alunos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @Past
    private LocalDate dataNascimento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
}
