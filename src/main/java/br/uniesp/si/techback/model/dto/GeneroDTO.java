package br.uniesp.si.techback.model.dto;

import jakarta.validation.constraints.NotBlank;

public record GeneroDTO(

        @NotBlank(message = "Campo descrição não pode ser vazio")
        String descricao) {
}
