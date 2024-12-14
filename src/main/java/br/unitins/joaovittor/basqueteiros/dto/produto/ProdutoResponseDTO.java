package br.unitins.joaovittor.basqueteiros.dto.produto;

import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.produto.Produto;
import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;

public record ProdutoResponseDTO (
    Long id,
    String nome,
    float preco,
    int estoque,
    FornecedorResponseDTO fornecedor,
    String descricao,
    String marca,
    String modelo,
    Tamanho tamanho
) {
    public static ProdutoResponseDTO valueOf(Produto produto) {
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(),
            produto.getPreco(),
            produto.getEstoque(),
            FornecedorResponseDTO.valueOf(produto.getFornecedor()),
            produto.getDescricao(),
            produto.getMarca() != null ? produto.getMarca().getNome() : null,
            produto.getModelo(),
            produto.getTamanho()
        );
    }
}

 