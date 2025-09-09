package br.uniesp.si.techback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filme {
    private Long id;
    private String titulo;
    private String genero;
    private int anoLancamento;
}
