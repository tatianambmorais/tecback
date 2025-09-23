package br.uniesp.si.techback.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EnderecoDTO(
        @NotBlank(message = "Campo logradouro não pode ser vazio")
        String logradouro,
        @NotNull(message = "Campo número não pode ser vazio")
        @Positive
        int numero,
        @NotBlank(message = "Campo cidade não pode ser vazio")
        String cidade,
        @NotBlank(message = "Campo bairro não pode ser vazio")
        String bairro,
        @NotBlank(message = "Campo estado não pode ser vazio")
        String estado,
        @NotBlank(message = "Campo cep não pode ser vazio")
        String cep) {
}
