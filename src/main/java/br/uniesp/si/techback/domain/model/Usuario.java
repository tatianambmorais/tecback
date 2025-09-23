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
    @OneToOne
    private Plano plano;
}
