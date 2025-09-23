package br.uniesp.si.techback.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotNull
        Long cpf,

        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @Email(message = "Email deve ser válido")
        String email,

        @NotNull(message = "Plano não pode ser nulo")
        Long planoId
) {}
