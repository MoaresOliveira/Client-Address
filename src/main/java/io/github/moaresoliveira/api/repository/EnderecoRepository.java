package io.github.moaresoliveira.api.repository;

import io.github.moaresoliveira.api.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

}
