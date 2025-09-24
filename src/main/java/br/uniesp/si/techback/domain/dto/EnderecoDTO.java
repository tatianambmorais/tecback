package br.uniesp.si.techback.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoDTO {
    @NotNull
    private Long id;

    @NotBlank(message = "Logradouro não pode ser vazio")
    private String logradouro;

    @NotNull(message = "Número não pode ser nulo")
    private Integer numero;

    @NotBlank(message = "Bairro não pode ser vazio")
    private String bairro;

    @NotBlank(message = "Cidade não pode ser vazia")
    private String cidade;

    @NotBlank(message = "Estado não pode ser vazio")
    @Size(max = 2, message = "Estado deve ter no máximo 2 caracteres")
    private String estado;

    @NotBlank(message = "CEP não pode ser vazio")
    @Size(max = 10, message = "CEP deve ter no máximo 10 caracteres")
    private String cep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
