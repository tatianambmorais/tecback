package br.uniesp.si.techback.domain.dto.request;

import br.uniesp.si.techback.domain.enums.TipoPlano;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PlanoRequestDTO(
        @NotNull(message = "Tipo do plano não pode ser nulo")
        TipoPlano tipoPlano,

        @Positive(message = "Preço deve ser maior que zero")
        double preco
) {}
