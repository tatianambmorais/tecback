package br.uniesp.si.techback.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record FilmeRequestDTO(
        @NotBlank(message = "Título não pode ser vazio")
        @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")
        String titulo,

        @NotBlank(message = "Sinopse não pode ser vazia")
        String sinopse,

        @NotNull(message = "Data de lançamento não pode ser nula")
        LocalDate dataLancamento,

        @NotNull(message = "ID do gênero não pode ser nulo")
        Long generoId,

        @NotNull(message = "Duração não pode ser nula")
        Integer duracaoMinutos,

        @NotBlank(message = "Classificação indicativa não pode ser vazia")
        @Size(max = 10, message = "Classificação indicativa deve ter no máximo 10 caracteres")
        String classificacaoIndicativa
) {}
