package br.com.registro.escola.repository;

import br.com.registro.escola.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Boolean existsByCpf(String cpf);

}
