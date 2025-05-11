package com.meuprojeto.gestaoEscolar.repository;

import com.meuprojeto.gestaoEscolar.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {

}
