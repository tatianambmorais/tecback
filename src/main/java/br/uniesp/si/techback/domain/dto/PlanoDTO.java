package br.uniesp.si.techback.domain.dto;

import br.uniesp.si.techback.domain.enums.TipoPlano;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PlanoDTO {
    private Long id;

    @NotNull(message = "Tipo do plano não pode ser nulo")
    private TipoPlano tipoPlano;

    @Positive(message = "Preço deve ser maior que zero")
    private double preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPlano getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(TipoPlano tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
