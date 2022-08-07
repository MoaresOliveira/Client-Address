package io.github.moaresoliveira.api.model;

import io.github.moaresoliveira.api.model.form.ClienteForm;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    private Endereco endereco;

}
