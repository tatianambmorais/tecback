package br.uniesp.si.techback.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    private Long cpf;
    private String nome;
    private String email;
    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}
