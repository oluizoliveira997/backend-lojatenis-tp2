package br.unitins.joaovittor.basqueteiros.dto.produto;

import java.math.BigDecimal;

import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
    @NotBlank(message = "Nome é obrigatório")
    String nome,
    @NotNull(message = "Preço é obrigatório")
    BigDecimal preco,
    @Min(value = 0, message = "Estoque não pode ser negativo")
    int estoque,
    @NotNull(message = "Fornecedor é obrigatório")
    FornecedorDTO fornecedor,
    String descricao,
    Long marcaId,
    String modelo,
    Tamanho tamanho
) 

{
}