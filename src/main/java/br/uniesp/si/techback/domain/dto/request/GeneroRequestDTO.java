package br.uniesp.si.techback.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GeneroRequestDTO(
        @NotBlank(message = "Descrição não pode ser vazia")
        @Size(max = 50, message = "Descrição deve ter no máximo 50 caracteres")
        String descricao
) {}