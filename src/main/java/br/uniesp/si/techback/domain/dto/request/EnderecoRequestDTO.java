package br.uniesp.si.techback.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(
        @NotNull
        Long id,

        @NotBlank(message = "Logradouro não pode ser vazio")
        String logradouro,

        @NotNull(message = "Número não pode ser nulo")
        Integer numero,

        @NotBlank(message = "Bairro não pode ser vazio")
        String bairro,

        @NotBlank(message = "Cidade não pode ser vazia")
        String cidade,

        @NotBlank(message = "Estado não pode ser vazio")
        @Size(max = 2, message = "Estado deve ter no máximo 2 caracteres")
        String estado,

        @NotBlank(message = "CEP não pode ser vazio")
        @Size(max = 10, message = "CEP deve ter no máximo 10 caracteres")
        String cep
) {}
