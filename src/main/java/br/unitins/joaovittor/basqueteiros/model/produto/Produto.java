package br.unitins.joaovittor.basqueteiros.model.produto;

import java.util.Objects;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;
//import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Produto extends DefaultEntity {

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @Column(nullable = false)
    private Float preco;

    @Min(value = 0, message = "Estoque não pode ser negativo")
    @Column(nullable = false)
    private int estoque;

    @NotNull(message = "Fornecedor é obrigatório")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    private String modelo;

    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;

    // Getters e Setters com validações
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = Objects.requireNonNull(preco, "Preço não pode ser nulo");
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.estoque = estoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = Objects.requireNonNull(fornecedor, "Fornecedor não pode ser nulo");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
}