package com.meuprojeto.gestaoEscolar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enderecos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String logradouro;

    private String numero;

    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;

}
