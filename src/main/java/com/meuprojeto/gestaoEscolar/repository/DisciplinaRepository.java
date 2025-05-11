package com.meuprojeto.gestaoEscolar.repository;


import com.meuprojeto.gestaoEscolar.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {


}
