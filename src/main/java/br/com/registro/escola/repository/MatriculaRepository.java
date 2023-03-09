package br.com.registro.escola.repository;

import br.com.registro.escola.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    Boolean existsByAlunoId(Long alunoId);

}
