package br.unitins.joaovittor.basqueteiros.service.produto;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.produto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.dto.produto.ProdutoResponseDTO;

public interface ProdutoService {
    // Métodos CRUD básicos
    List<ProdutoResponseDTO> getAll();
    ProdutoResponseDTO getById(Long id);
    ProdutoResponseDTO create(ProdutoDTO produtoDTO);
    ProdutoResponseDTO update(Long id, ProdutoDTO produtoDTO);
    void delete(Long id);

    // Métodos de busca adicionais
    List<ProdutoResponseDTO> findByNome(String nome);
    List<ProdutoResponseDTO> findByMarca(Long marcaId);
    List<ProdutoResponseDTO> findByFornecedor(Long fornecedorId);
    List<ProdutoResponseDTO> findByPrecoBetween(Float precoMin, Float precoMax);
    
    // Métodos de atualização parcial
    ProdutoResponseDTO updateEstoque(Long id, int quantidade);
    ProdutoResponseDTO updatePreco(Long id, Float novoPreco);
    
    // Métodos de contagem
    long count();
    long countByMarca(Long marcaId);
    long countByFornecedor(Long fornecedorId);
}